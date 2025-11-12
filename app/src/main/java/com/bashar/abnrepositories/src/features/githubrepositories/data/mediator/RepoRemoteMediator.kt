package com.bashar.abnrepositories.src.features.githubrepositories.data.mediator


import androidx.paging.*
import androidx.room.withTransaction
import com.bashar.abnrepositories.src.core.data.local.room.RepoDatabase
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.RepoEntity
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.toEntity
import com.bashar.abnrepositories.src.features.githubrepositories.data.mapper.toDomain
import com.bashar.abnrepositories.src.features.githubrepositories.data.remote.GitHubApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RepoRemoteMediator(
    private val api: GitHubApi,
    private val db: RepoDatabase,
    private val pageSize: Int
) : RemoteMediator<Int, RepoEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepoEntity>
    ): MediatorResult {
        return try {
            //Load key
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(true)
                    // Compute next page based on how many items we already have in DB
                    val currentCount = db.repoDao().count()
                    (currentCount / pageSize) + 1
                }
            }

            val perPage = if (loadType == LoadType.REFRESH) state.config.initialLoadSize else pageSize

            val response = api.getRepos(page = page, perPage = perPage)

            val endOfPaginationReached = response.isEmpty()

            db.withTransaction {

                if (loadType == LoadType.REFRESH) db.repoDao().clearAll()

                val startIndex = db.repoDao().count()
                val repoEntities = response.mapIndexed { index, dto ->
                    dto.toDomain().toEntity().copy(remoteIndex = startIndex + index)
                }
                db.repoDao().insertAll(repoEntities)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (ioe: IOException) {

            val hasCache = db.repoDao().count() > 0
            if (hasCache) {
                 MediatorResult.Success(endOfPaginationReached = false)
            } else {
                 MediatorResult.Error(ioe)
            }

        } catch (he: HttpException) {
            MediatorResult.Error(he)
        }
    }
}
