package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orafaelsc.cstvfuze.domain.model.Player
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors

@Composable
fun PlayerCard(
    modifier: Modifier = Modifier,
    player: Player,
    showAvatarAtStart: Boolean = true,
) {
    val shape =
        if (!showAvatarAtStart) {
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 12.dp,
                bottomEnd = 12.dp,
                bottomStart = 0.dp,
            )
        } else {
            RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp,
                bottomStart = 12.dp,
            )
        }

    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(shape)
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        if (showAvatarAtStart) {
            PlayerAvatar(
                playerName = player.name,
                playerAvatarUrl = player.imageUrl,
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = if (showAvatarAtStart) Alignment.Start else Alignment.End,
        ) {
            Text(
                text = player.name,
                color = ExtendedColors.Default.textPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = player.firstName + " " + player.lastName,
                color = ExtendedColors.Default.textSecondary,
                fontSize = 12.sp,
                maxLines = 1,
            )
        }

        if (!showAvatarAtStart) {
            PlayerAvatar(
                playerName = player.slug,
                playerAvatarUrl = player.imageUrl,
            )
        }
    }
}

@Preview
@Composable
fun PlayerCardPreview() {
    PlayerCard(
        player =
            Player(
                active = true,
                id = 24106,
                name = "junior",
                role = null,
                slug = "junior",
                modifiedAt = "2025-07-19T17:13:13Z",
                firstName = "Paytyn",
                lastName = "Johnson",
                nationality = "US",
                imageUrl = "https://cdn.pandascore.co/images/player/image/24106/465px_junior_triumph.png",
            ),
        showAvatarAtStart = false,
    )
}
