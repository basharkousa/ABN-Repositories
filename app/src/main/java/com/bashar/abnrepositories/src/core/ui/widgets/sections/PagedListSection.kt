package com.bashar.abnrepositories.src.core.ui.widgets.sections


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh

import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bashar.abnrepositories.R
import com.bashar.abnrepositories.src.core.utils.ApiErrorParser
import com.bashar.abnrepositories.src.core.utils.NetworkMonitor

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
    val context = LocalContext.current
    val isConnected by NetworkMonitor.observe(context)
        .collectAsState(initial = NetworkMonitor.isConnected(context))
    var wasOffline by remember { mutableStateOf(false) }

    LaunchedEffect(isConnected) {
//        Toast.makeText(context,"isConnected $isConnected",Toast.LENGTH_SHORT ).show()
        if (isConnected && wasOffline) {
            // trigger refresh when connection comes back
            pagingItems.retry()
            wasOffline = false
        } else if (!isConnected) {
            wasOffline = true
        }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            onRefresh()
            pagingItems.refresh()
        },
        modifier = modifier.fillMaxSize()
    ) {
        Box(Modifier.fillMaxSize()) {
            val refreshState = pagingItems.loadState.refresh
            when (refreshState) {
                is LoadState.Loading -> if (pagingItems.itemCount == 0) {
                    LoadingStateWidget()
                }
//                is LoadState.Error ->
//                    ErrorStateWidget(
//                    message = refreshState.error.localizedMessage ?: "Error occurred",
//                    onRetry = onRetry
//                )
                else -> {
                    LaunchedEffect(refreshState) {
                        if (refreshState is LoadState.Error){
                            val message = ApiErrorParser.getMessage(refreshState.error)
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize()) {
                    if(pagingItems.itemCount > 0){
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = contentPadding,
                            verticalArrangement = verticalArrangement
                        ) {
                            items(pagingItems.itemCount, key ={ index ->
                                index
                            }) { index ->
                                val item = pagingItems[index]
                                if (item != null) itemContent(item)
                            }
                            when (pagingItems.loadState.append) {
                                is LoadState.Loading -> item { ListFooterLoadingWidget() }
                                is LoadState.Error -> item {
                                    ListFooterErrorWidget({
                                        pagingItems.retry()
                                        onRetry()
                                    })
                                }
                                else -> {}
                            }
                        }
                    } else {
                        Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                            Text(stringResource(R.string.no_items), style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }}
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

//To use when working only with api without cashing!!
@Composable
private fun ErrorStateWidget(message: String, onRetry: () -> Unit) =
    Box(Modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(message)
            Spacer(Modifier.height(8.dp))
            Button(onClick = onRetry) { Text(stringResource(R.string.retry)) }
        }
    }

@Composable
private fun ListFooterErrorWidget(onRetry: () -> Unit) =
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextButton(onClick = onRetry) { Icon(Icons.Default.Refresh, null) }
    }


