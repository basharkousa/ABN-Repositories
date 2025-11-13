package com.bashar.abnrepositories.src.features.githubrepositories.data.mediator


import androidx.paging.*
import androidx.room.withTransaction
import com.bashar.abnrepositories.src.core.data.local.room.RepoDatabase
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.RepoEntity
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.RepoRemoteKeys
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

    private val REPO_LIST_KEY = "github_repo_list"

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
                    // Look up the next page key from the dedicated table
                    val remoteKeys = db.repoRemoteKeysDao().remoteKeyByRepoId(REPO_LIST_KEY)
                    // If remoteKeys is null, it means REFRESH hasn't run successfully yet
                    remoteKeys?.nextKey ?: 1
                }
            }

            val perPage = pageSize
            val response = api.getRepos(page = page, perPage = perPage)

            val endOfPaginationReached = response.isEmpty()

            db.withTransaction {
                // 2 Clear both Repos and RemoteKeys on REFRESH
                if (loadType == LoadType.REFRESH) {
                    db.repoDao().clearAll()
                    db.repoRemoteKeysDao().clearRemoteKeys()
                }

                val startIndex = db.repoDao().count()
                val repoEntities = response.mapIndexed { index, dto ->
                    dto.toDomain().toEntity().copy(remoteIndex = startIndex + index)
                }
                db.repoDao().insertAll(repoEntities)

                // 3. Save the key for the next load
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = RepoRemoteKeys(repoId = REPO_LIST_KEY, nextKey = nextKey)
                db.repoRemoteKeysDao().insertOrReplace(keys)

            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (ioe: IOException) {

//            val hasCache = db.repoDao().count() > 0
//            if (hasCache) {
//                 MediatorResult.Success(endOfPaginationReached = false)
//            } else {
            MediatorResult.Error(ioe)
//            }

        } catch (he: HttpException) {
            MediatorResult.Error(he)
        }
    }
}
