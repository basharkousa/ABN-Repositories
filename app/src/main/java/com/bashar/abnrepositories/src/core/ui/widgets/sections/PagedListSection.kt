package com.bashar.abnrepositories.src.core.ui.widgets.sections


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

/**
 * Generic pagination + pull-to-refresh section.
 *
 * Uses the new PullToRefreshBox() from Material3.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Any> PaginatedSection(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    pagingItems: LazyPagingItems<T>,
    onRefresh: () -> Unit,
    onRetry: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(12.dp),
    itemContent: @Composable (T) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier.fillMaxSize()
    ) {
        Box(Modifier.fillMaxSize()) {
            val refreshState = pagingItems.loadState.refresh

            when (refreshState) {
                is LoadState.Loading -> if (pagingItems.itemCount == 0) {
                    LoadingStateWidget()
                }

                is LoadState.Error -> ErrorStateWidget(
                    message = refreshState.error.localizedMessage ?: "Error occurred",
                    onRetry = onRetry
                )

                else -> {}
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = contentPadding,
                verticalArrangement = verticalArrangement
            ) {
                items(pagingItems.itemCount, key = { it.hashCode() }) { index ->
                    val item = pagingItems[index]
                    if (item != null) itemContent(item)
                }

                when (val append = pagingItems.loadState.append) {
                    is LoadState.Loading -> item { ListFooterLoadingWidget() }
                    is LoadState.Error -> item { ListFooterErrorWidget(onRetry) }
                    else -> {}
                }
            }
        }
    }
}

@Composable
private fun ListFooterLoadingWidget() =
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }


@Composable
private fun LoadingStateWidget() =
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }

@Composable
private fun ErrorStateWidget(message: String, onRetry: () -> Unit) =
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(message)
            Spacer(Modifier.height(8.dp))
            Button(onClick = onRetry) { Text("Retry") }
        }
    }

@Composable
private fun ListFooterErrorWidget(onRetry: () -> Unit) =
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextButton(onClick = onRetry) { Text("Retry loading more") }
    }


