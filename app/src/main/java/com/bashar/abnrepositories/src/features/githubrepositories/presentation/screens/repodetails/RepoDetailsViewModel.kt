package com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repodetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashar.abnrepositories.src.core.utils.fromJson
import com.bashar.abnrepositories.src.features.githubrepositories.domain.RepoRepository
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val argument: String? = savedStateHandle["repo"]

    private val _state = MutableStateFlow(RepoDetailsState())
    val state = _state.asStateFlow()



    init {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                repo = argument?.fromJson(Repo::class.java)
            )
        }
    }

    fun onEvent(event: RepoDetailEvent) {
        when (event) {
            RepoDetailEvent.OnOpenInBrowser -> openInBrowser()
            else -> {}
        }
    }

    private fun loadRepoDetail(fullName: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                // 🔹 Simplify: reuse cached data if available from your DB later
//                val repo = repository.getRepoByFullName(fullName)
//                _state.update { it.copy(repo = repo, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    private fun openInBrowser() {
        // handled in UI
    }
}
