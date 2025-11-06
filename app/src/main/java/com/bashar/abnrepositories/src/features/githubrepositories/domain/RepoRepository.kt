package com.bashar.abnrepositories.src.features.githubrepositories.domain


import androidx.paging.PagingData
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun pagedRepos(pageSize: Int = 5): Flow<PagingData<Repo>>
}
