package com.bashar.abnrepositories.src.features.githubrepositories.presentation.screens.repodetails

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun RepoDetailsScreen(
    onBack: () -> Unit,
    onOpenInBrowser: (repoUrl: String) -> Unit,
    viewModel: RepoDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current


    RepoDetailContent(
        state = state,
        onEvent = { event ->
            when (event) {
                RepoDetailEvent.OnBackPress -> onBack()
                RepoDetailEvent.OnOpenInBrowser -> {
                    state.repo?.htmlUrl?.let {
                        onOpenInBrowser(it)
                    }
                }
                else -> {}
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoDetailContent(
    state: RepoDetailsState,
    onEvent: (RepoDetailEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Repository Details") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(RepoDetailEvent.OnBackPress) }) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        when {
            state.isLoading -> Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            state.error != null -> Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) { Text(state.error ?: "Error") }

            else -> state.repo?.let { repo ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = repo.ownerAvatar,
                            contentDescription = "Owner avatar",
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(repo.name, style = MaterialTheme.typography.titleLarge)
                            Text(repo.fullName, style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    Divider()

                    repo.description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AssistChip(
                            onClick = {},
                            label = { Text(if (repo.isPrivate) "Private" else "Public") }
                        )
                        AssistChip(
                            onClick = {},
                            label = { Text(repo.visibility) }
                        )
                    }

                    Spacer(Modifier.height(24.dp))
                    Button(
                        onClick = { onEvent(RepoDetailEvent.OnOpenInBrowser) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Open in Browser")
                    }
                }
            }
        }
    }
}
