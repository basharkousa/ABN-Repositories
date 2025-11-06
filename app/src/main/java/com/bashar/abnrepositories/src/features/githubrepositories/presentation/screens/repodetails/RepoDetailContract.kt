package com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repodetails

import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo

data class RepoDetailsState(
    val isLoading: Boolean = false,
    val repo: Repo? = null,
    val error: String? = null
)

sealed class RepoDetailEvent{
    data object OnBackPress : RepoDetailEvent()
    data object OnOpenInBrowser : RepoDetailEvent()
}