package com.orafaelsc.cstvfuze.ui.matchdetails
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.ui.components.MatchHeader
import com.orafaelsc.cstvfuze.ui.components.PlayersSection

@Composable
fun MatchDetailsScreenPortrait(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    match: Match
) {
    LazyColumn(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            MatchHeader(match = match)
        }

        item {
            PlayersSection(
                team1Players = match.firstTeam.players.orEmpty(),
                team2Players = match.secondTeam.players.orEmpty()
            )
        }
    }
}
