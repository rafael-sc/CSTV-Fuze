package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.domain.model.Player
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors

@Composable
fun PlayerCard(
    modifier: Modifier = Modifier,
    player: Player,
    showAvatarAtStart: Boolean = true
) {
    val shape = if (!showAvatarAtStart) {
        RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 12.dp,
            bottomEnd = 12.dp,
            bottomStart = 0.dp
        )
    } else {
        RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 0.dp,
            bottomEnd = 0.dp,
            bottomStart = 12.dp
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(shape)
            .background(MaterialTheme.colorScheme.onPrimaryContainer)
            .padding(vertical = 8.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (showAvatarAtStart) {
            PlayerAvatar(
                playerName = player.name,
                playerAvatarUrl = player.imageUrl
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = if (showAvatarAtStart) Alignment.Start else Alignment.End
        ) {
            Text(
                text = player.name,
                color = ExtendedColors.Default.textPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = player.firstName + " " + player.lastName,
                color = ExtendedColors.Default.textSecondary,
                fontSize = 12.sp,
                maxLines = 1
            )
        }

        if (!showAvatarAtStart) {
            PlayerAvatar(
                playerName = player.slug,
                playerAvatarUrl = player.imageUrl
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
            .size(60.dp)
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
            Image(
                painter = painterResource(id = R.drawable.img_team_placeholder),
                contentDescription = "Default avatar",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview
@Composable
fun PlayerCardPreview(

) {
    PlayerCard(
        player = Player(
            active = true,
            id = 24106,
            name = "junior",
            role = null,
            slug = "junior",
            modifiedAt = "2025-07-19T17:13:13Z",
            firstName = "Paytyn",
            lastName = "Johnson",
            nationality = "US",
            imageUrl = "https://cdn.pandascore.co/images/player/image/24106/465px_junior_triumph.png"
        ),
        showAvatarAtStart = false
    )
}
