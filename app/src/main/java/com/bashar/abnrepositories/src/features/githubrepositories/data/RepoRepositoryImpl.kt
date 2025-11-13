package com.bashar.abnrepositories.src.features.githubrepositories.data


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bashar.abnrepositories.src.core.data.local.room.RepoDatabase
import com.bashar.abnrepositories.src.features.githubrepositories.data.local.toDomain
import com.bashar.abnrepositories.src.features.githubrepositories.data.mediator.RepoRemoteMediator
import com.bashar.abnrepositories.src.features.githubrepositories.data.remote.GitHubApi
import com.bashar.abnrepositories.src.features.githubrepositories.domain.RepoRepository
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val api: GitHubApi,
    private val db: RepoDatabase
) : RepoRepository{

/*    override fun pagedRepos(pageSize: Int): Flow<PagingData<Repo>> =
         Pager(
//            config = PagingConfig(pageSize = pageSize, prefetchDistance = pageSize / 2),
            config = PagingConfig( pageSize = pageSize, prefetchDistance = pageSize/2),
            pagingSourceFactory = { GitHubReposPagingSource(api, pageSize) }
        ).flow*/

    @OptIn(ExperimentalPagingApi::class)
    override fun pagedRepos(pageSize: Int): Flow<PagingData<Repo>> {
        val pagingSourceFactory = { db.repoDao().pagingSource() }
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 1
            ),
            remoteMediator = RepoRemoteMediator(api, db, pageSize),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}
