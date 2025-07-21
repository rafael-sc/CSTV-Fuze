package com.orafaelsc.cstvfuze.ui.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.ui.components.CustomTopAppBar
import com.orafaelsc.cstvfuze.ui.components.PullToRefreshBox
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchesViewModel = koinViewModel<MatchesViewModel>(),
    onMatchClick: (Match) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val isLandscape = LocalWindowInfo.current.containerSize.run { width > height }

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
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.fetchMatches() },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            when {
                uiState.isLoading -> {
//                   //as pull to refresh box has its own loading state, theres
                    //                   no need to show this  until pagination is implemented
//                    Box(
//                        modifier =
//                            Modifier
//                                .fillMaxSize()
//                                .padding(16.dp),
//                        contentAlignment = Alignment.Center,
//                    ) {
//                        CircularProgressIndicator(
//                            color = MaterialTheme.colorScheme.primary,
//                        )
//                    }
                }

                uiState.error != null -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column {
                            Text(
                                text = uiState.error ?: stringResource(R.string.failed_to_load_matches),
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
                    if (isLandscape) {
                        MatchesLandscape(
                            matches = uiState.matches,
                            onMatchClick = onMatchClick,
                        )
                    } else {
                        MatchesPortrait(
                            matches = uiState.matches,
                            onMatchClick = onMatchClick,
                        )
                    }
                }
            }
        }
    }
}
