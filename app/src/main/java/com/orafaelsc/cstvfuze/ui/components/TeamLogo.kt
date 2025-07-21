package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors

@Composable
fun TeamItem(
    modifier: Modifier = Modifier,
    teamName: String,
    teamLogoUrl: String? = null,
) {
    Column(
        modifier = modifier.size(70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TeamLogo(
            modifier = Modifier,
            teamName = teamName,
            teamLogoUrl = teamLogoUrl,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = teamName,
            color = ExtendedColors.Default.textPrimary,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Composable
private fun TeamLogo(
    modifier: Modifier = Modifier,
    teamName: String,
    teamLogoUrl: String? = null,
) {
    if (teamLogoUrl != null) {
        AsyncImage(
            modifier = Modifier.size(40.dp),
            model = teamLogoUrl,
            contentDescription = "$teamName logo",
        )
    } else {
        Text(
            text = teamName.replaceFirstChar { it.uppercase() },
            modifier = modifier,
            color = ExtendedColors.Default.textSecondary,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}
