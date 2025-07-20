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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orafaelsc.cstvfuze.domain.model.Player

@Composable
fun PlayersSection(
    modifier: Modifier = Modifier,
    team1Players: List<Player>,
    team2Players: List<Player>
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


@Preview
@Composable
fun PlayersSectionPreview() {

    val basePlayer = Player(
        id = 1321,
        name = "JD",
        slug = "JD",
        active = true,
        role = "AWP",
        modifiedAt = "2023-10-01T12:00:00Z" ,
        firstName = "John",
        lastName = "Doe",
        nationality = "nationality",
        imageUrl = "https://example.com/jd.png"
    )

    val team1Players = listOf(
        basePlayer.copy(name = "Player 1", firstName = "Diogo", lastName = "Alves", imageUrl = "https://example.com/player1.png"),
        basePlayer.copy(name = "Player 2", imageUrl = "https://example.com/player2.png"),
    )
    val team2Players = listOf(
        basePlayer.copy(name = "Player 3", imageUrl = "https://example.com/player3.png"),
        basePlayer.copy(name = "Player 4", imageUrl = "https://example.com/player4.png"),
    )



    PlayersSection(modifier = Modifier, team1Players, team2Players)
}
