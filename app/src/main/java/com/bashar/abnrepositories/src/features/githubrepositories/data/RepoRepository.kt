package com.bashar.abnrepositories.src.features.githubrepositories.data


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bashar.abnrepositories.src.features.githubrepositories.data.paging.GitHubReposPagingSource
import com.bashar.abnrepositories.src.features.githubrepositories.data.remote.GitHubApi
import com.bashar.abnrepositories.src.features.githubrepositories.domain.RepoRepository
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : RepoRepository{

    override fun pagedRepos(pageSize: Int): Flow<PagingData<Repo>> =
        Pager(
            config = PagingConfig(pageSize = pageSize, prefetchDistance = pageSize / 2),
            pagingSourceFactory = { GitHubReposPagingSource(api, pageSize) }
        ).flow
}
