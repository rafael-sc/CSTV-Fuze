package com.orafaelsc.cstvfuze.ui.matchdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.Player
import com.orafaelsc.cstvfuze.ui.components.MatchHeader
import com.orafaelsc.cstvfuze.ui.components.PlayerCard
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors

@Composable
fun MatchDetailsScreenLandscape(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    match: Match
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Left side - Match info and teams
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MatchHeader(match = match)
        }

        // Right side - Players in two columns
        Row(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Team 1 players column
            PlayerColumnHorizontal(
                modifier = Modifier.weight(1f),
                teamName = match.firstTeam.name,
                players = match.firstTeam.players.orEmpty()
            )

            // Team 2 players column
            PlayerColumnHorizontal(
                modifier = Modifier.weight(1f),
                teamName = match.secondTeam.name,
                players = match.secondTeam.players.orEmpty()
            )
        }
    }
}


@Composable
fun PlayerColumnHorizontal(
    teamName: String,
    players: List<Player>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        Text(
            text = teamName,
            color = ExtendedColors.Default.textPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp),
            textAlign = TextAlign.Center
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(players.size) {
                PlayerCard(modifier = Modifier, player = players[it], showAvatarAtStart = true)
            }
        }
    }
}
