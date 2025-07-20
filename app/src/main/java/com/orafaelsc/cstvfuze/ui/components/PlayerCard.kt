package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.orafaelsc.cstvfuze.domain.model.Player
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors

@Composable
fun PlayerCard(
    modifier: Modifier = Modifier,
    player: Player
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Player avatar
        PlayerAvatar(
            playerName = player.nickname,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Player info
        Column {
            Text(
                text = player.nickname,
                color = ExtendedColors.Default.textPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = player.nickname,
                color = ExtendedColors.Default.textSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun PlayerAvatar(
    modifier: Modifier = Modifier,
    playerName: String,
    playerAvatarUrl: String? = null
) {
    Box(
        modifier = modifier
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (playerAvatarUrl != null) {
            AsyncImage(
                model = playerAvatarUrl,
                contentDescription = "$playerName avatar",
                modifier = Modifier.fillMaxWidth()
            )
        } else if (playerName.isNotEmpty()) {
            Text(
                text = playerName.take(2).uppercase(),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview
@Composable
fun PlayerCardPreview() {
    PlayerCard(
        player = Player(
            id = 1,
            nickname = "PlayerOne",
            firstName = "Player",
            lastName = "One",
            imageUrl = "https://cdn.pandascore.co/images/player/image/30051/66394813_365379094166923_7854191081989079040_n.png"
        )
    )
}
