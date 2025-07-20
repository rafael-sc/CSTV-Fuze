package com.orafaelsc.cstvfuze.ui.matches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.ui.components.MatchCard

@Composable
internal fun MatchesPortrait(
    matches: List<Match>,
    onMatchClick: (Match) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(matches.size) { index ->
            MatchCard(
                match = matches[index],
                onClick = { onMatchClick(matches[index]) },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
