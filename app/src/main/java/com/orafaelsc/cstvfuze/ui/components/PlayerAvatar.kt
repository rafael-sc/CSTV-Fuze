package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.orafaelsc.cstvfuze.R

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
        if (playerAvatarUrl.isNullOrEmpty().not()) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = playerAvatarUrl,
                contentDescription = "$playerName avatar"
            )
        } else{
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.img_team_placeholder),
                contentDescription = "Default avatar"
            )
        }
    }
}

@Preview
@Composable
fun PlayerAvatarPreview() {
    PlayerAvatar(
        playerName = "Mike Jones",
        playerAvatarUrl = "",
        modifier = Modifier.testTag("playerAvatar")
    )
}
