package com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bashar.abnrepositories.src.features.githubrepositories.domain.RepoRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val repository: RepoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RepoListState())
    val state = _state.asStateFlow()

    init {
        loadRepos()
    }

    private fun loadRepos(){
        _state.update {
            it.copy(
                repos = repository.pagedRepos(pageSize = 5).cachedIn(viewModelScope),
                isLoading = false,
                error = null
            )
        }
    }

    fun onEvent(event: RepoListEvent) {
        when (event) {
            is RepoListEvent.OnRefresh -> {
                _state.update { it.copy(isLoading = true) }
                loadRepos()
            }

            is RepoListEvent.OnRepoClick -> {
                // You’ll handle navigation externally
            }

            RepoListEvent.OnNavigateToSetting ->{

            }

            is RepoListEvent.OnRetry -> loadRepos()
        }
    }
}
