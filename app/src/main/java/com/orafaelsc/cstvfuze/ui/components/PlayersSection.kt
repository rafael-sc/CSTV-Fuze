package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orafaelsc.cstvfuze.domain.model.Player

@Composable
fun PlayersSection(
    team1Players: List<Player>,
    team2Players: List<Player>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Players grid
        val maxPlayers = maxOf(team1Players.size, team2Players.size)

        for (i in 0 until maxPlayers) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Team 1 player
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (i < team1Players.size) {
                        PlayerCard(player = team1Players[i], showAvatarAtStart = false)
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Team 2 player
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (i < team2Players.size) {
                        PlayerCard(player = team2Players[i], showAvatarAtStart = true)
                    }
                }
            }
        }
    }
}
