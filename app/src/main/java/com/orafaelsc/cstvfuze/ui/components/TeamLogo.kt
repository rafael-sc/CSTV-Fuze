package com.orafaelsc.cstvfuze.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun TeamItem(
    modifier: Modifier = Modifier,
    teamName: String,
    teamLogoUrl: String? = null
) {
    Column(
        modifier = modifier.size(70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateTeamLogo(
            modifier = Modifier,
            teamName = teamName,
            teamLogoUrl = teamLogoUrl
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = teamName,
            color = Color.Black, // todo set textPrimary from theme,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
private fun CreateTeamLogo(
    modifier: Modifier = Modifier,
    teamName: String,
    teamLogoUrl: String? = null
) {
    if (teamLogoUrl != null) {
        AsyncImage(
            modifier = Modifier.size(40.dp),
            model = teamLogoUrl,
            contentDescription = "$teamName logo"
        )
    } else {
        Text(
            text = teamName.take(1).uppercase(), // Example: Display first two letters of team name
            modifier = modifier,
            color = Color.White, // todo set textPrimary from theme
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
