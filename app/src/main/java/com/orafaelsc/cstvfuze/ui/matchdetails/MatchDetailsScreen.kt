package com.orafaelsc.cstvfuze.ui.matchdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.ui.components.CustomTopAppBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesDetailsScreen(
    modifier: Modifier,
    match: Match,
    viewModel: MatchDetailsViewModel = koinViewModel<MatchDetailsViewModel>(),
    onBackClick: () -> Unit = { },
) {
    val uiState by viewModel.uiState.collectAsState()
    val isLandscape = LocalWindowInfo.current.containerSize.run { width > height }

    LaunchedEffect(match) {
        viewModel.loadMatchData(match)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                title = match.description,
                backButtonContentDescription = stringResource(R.string.match_details_back_description),
                onBackClick = onBackClick,
            )
        },
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                LoadingContent(paddingValues = paddingValues)
            }

            uiState.error != null -> {
                MatchErrorContent(
                    paddingValues = paddingValues,
                    errorMessage = uiState.error.toString(),
                    fallbackMatch = match
                )
            }

            else -> {
                val displayMatch = uiState.match ?: match
                if (isLandscape) {
                    MatchDetailsScreenLandscape(paddingValues = paddingValues, match = displayMatch)
                } else {
                    MatchDetailsScreenPortrait(paddingValues = paddingValues, match = displayMatch)
                }
            }
        }
    }
}

@Composable
private fun LoadingContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

