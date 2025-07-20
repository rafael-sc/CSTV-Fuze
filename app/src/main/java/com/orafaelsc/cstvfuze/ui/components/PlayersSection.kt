package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orafaelsc.cstvfuze.domain.model.Player
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors

@Composable
fun PlayersSection(
    modifier: Modifier = Modifier,
    team1Players: List<Player>,
    team2Players: List<Player>,
) {
    val maxPlayerCount = maxOf(team1Players.size, team2Players.size)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        if (team1Players.isEmpty() && team2Players.isEmpty()) {
            Text(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                text = "Player list not available",
                style = MaterialTheme.typography.bodyMedium,
                color = ExtendedColors.Default.textPrimary,
            )
        } else {
            repeat(maxPlayerCount) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    // Team 1 player
                    Box(modifier = Modifier.weight(1f)) {
                        if (index < team1Players.size) {
                            PlayerCard(
                                modifier = Modifier,
                                player = team1Players[index],
                                showAvatarAtStart = false,
                            )
                        } else {
                            // Empty space when team1 has fewer players
                            Spacer(modifier = Modifier)
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Team 2 player
                    Box(modifier = Modifier.weight(1f)) {
                        if (index < team2Players.size) {
                            PlayerCard(
                                modifier = Modifier,
                                player = team2Players[index],
                                showAvatarAtStart = true,
                            )
                        } else {
                            // Empty space when team2 has fewer players
                            Spacer(modifier = Modifier)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PlayersSectionPreview() {
    val basePlayer =
        Player(
            id = 1321,
            name = "JD",
            slug = "JD",
            active = true,
            role = "AWP",
            modifiedAt = "2023-10-01T12:00:00Z",
            firstName = "John",
            lastName = "Doe",
            nationality = "nationality",
            imageUrl = "https://example.com/jd.png",
        )

    val team1Players = emptyList<Player>()
    val team2Players =
        listOf(
            basePlayer.copy(id = 1, name = "Player1"),
            basePlayer.copy(id = 2, name = "Player2"),
        )

    PlayersSection(modifier = Modifier, team1Players, team2Players)
}
