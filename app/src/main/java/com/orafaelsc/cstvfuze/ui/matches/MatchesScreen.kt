package com.orafaelsc.cstvfuze.ui.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.ui.components.CustomTopAppBar
import com.orafaelsc.cstvfuze.ui.components.MatchCard
import com.orafaelsc.cstvfuze.ui.components.PullToRefreshBox
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchesViewModel = koinViewModel<MatchesViewModel>(),
    onMatchClick: (Match) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState().value
    val isLoading = uiState.isLoading

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                title = stringResource(R.string.matches),
            )
        },
    ) { paddingValues ->

        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = { viewModel.fetchMatches() },
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                uiState.error != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column {
                            Text(
                                text = uiState.error,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error,
                            )
                            TextButton(
                                onClick = { viewModel.fetchMatches() },
                            ) {
                                Text(
                                    modifier = Modifier.padding(12.dp),
                                    text = stringResource(R.string.retry),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.error,
                                )
                            }
                        }

                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(uiState.matches.size) { index ->
                            MatchCard(
                                match = uiState.matches[index],
                                onClick = { onMatchClick(uiState.matches[index]) },
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }
                }
            }
        }
    }
}
