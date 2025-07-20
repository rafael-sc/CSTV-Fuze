package com.orafaelsc.cstvfuze.ui.matchdetails
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.ui.components.MatchHeader
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors

@Composable
fun MatchErrorContent(
    paddingValues: PaddingValues,
    errorMessage: String,
    fallbackMatch: Match,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
    ) {
        // Show error message
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        // Still show the basic match info even on error
        MatchHeader(match = fallbackMatch)

        Text(
            text = stringResource(R.string.failed_to_load_team_details),
            color = ExtendedColors.Default.textSecondary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}
