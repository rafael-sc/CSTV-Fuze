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
import androidx.compose.ui.res.painterResource
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
