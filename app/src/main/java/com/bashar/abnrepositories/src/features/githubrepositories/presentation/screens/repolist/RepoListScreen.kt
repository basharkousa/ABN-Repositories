package com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repolist

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bashar.abnrepositories.src.core.ui.widgets.sections.PaginatedSection
import com.bashar.abnrepositories.src.features.githubrepositories.presentation.widgets.items.RepoItem
import com.bashar.abnrepositories.R
import com.bashar.abnrepositories.src.core.ui.theme.WestMoscow
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo

@Composable
fun RepoListScreen(
    onNavigateToDetail: (repo: Repo) -> Unit,
    onNavigateToSetting: () -> Unit,
    viewModel: RepoListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    RepoListContent(
        state = state,
        onEvent = { event ->
            when (event) {
                is RepoListEvent.OnRepoClick -> {
                    onNavigateToDetail(event.repo)
                }

                is RepoListEvent.OnNavigateToSetting -> {
                    onNavigateToSetting()
                }

                else -> viewModel.onEvent(event)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListContent(
    state: RepoListState,
    onEvent: (RepoListEvent) -> Unit
) {
    val pagingItems = state.repos.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    stringResource(R.string.abn_amro_repos),
                    fontFamily = WestMoscow
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
//                containerColor = MaterialTheme.colorScheme.onBackground,
                onClick = {
                    onEvent(RepoListEvent.OnNavigateToSetting)
                }) {
                Icon(Icons.Filled.Settings, "Settings")
            }
        }
    ) { padding ->

        PaginatedSection(
            modifier = Modifier.padding(padding),
            isRefreshing = state.isLoading,
            pagingItems = pagingItems,
            onRefresh = {
                onEvent(RepoListEvent.OnRefresh) },
            onRetry = { onEvent(RepoListEvent.OnRetry) },
        ) { repo ->
            RepoItem(repo) {
                onEvent(RepoListEvent.OnRepoClick(repo))
            }
        }
    }
}
