package com.orafaelsc.cstvfuze.ui.matchdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    viewModel.loadMatchData(match)

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                title = stringResource(R.string.match_details),
                backButtonContentDescription = stringResource(R.string.match_details_back_description),
                onBackClick = onBackClick,
            )
        },
    ) { paddingValues ->
        Text(
            text = stringResource(R.string.match_details) + match,
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
