package com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repolist

import androidx.paging.PagingData
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class RepoListState(
    val repos: Flow<PagingData<Repo>> = emptyFlow(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class RepoListEvent {
    data object OnRefresh : RepoListEvent()
    data object OnNavigateToSetting : RepoListEvent()
    data class OnRepoClick(val repo: Repo) : RepoListEvent()
    data object OnRetry : RepoListEvent()
}