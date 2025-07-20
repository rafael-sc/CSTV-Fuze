package com.orafaelsc.cstvfuze.ui.matchdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.ui.components.MatchHeader
import com.orafaelsc.cstvfuze.ui.components.PlayerCard
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors

@Composable
fun MatchDetailsScreenLandscape(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    match: Match
) {
    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MatchHeader(match = match)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Team 1 players
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = match.firstTeam.name,
                    color = ExtendedColors.Default.textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                val team1Players = match.firstTeam.players
                team1Players?.forEach { player ->
                    PlayerCard(player = player)
                }
            }

            // Team 2 players
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = match.secondTeam.name,
                    color = ExtendedColors.Default.textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                val team2Players = match.secondTeam.players
                team2Players?.forEach { player ->
                    PlayerCard(player = player)
                }
            }
        }
    }
}
