package com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repodetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bashar.abnrepositories.src.core.utils.fromJson
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
}
