package com.orafaelsc.cstvfuze.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.domain.model.Team
import com.orafaelsc.cstvfuze.domain.model.isLive
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors
import com.orafaelsc.cstvfuze.util.toLocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun MatchCard(
    modifier: Modifier = Modifier,
    match: Match,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = 16.dp, vertical = 8.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            TagComponent(
                backgroundColor = if (match.isLive()) Color.Red else Color.DarkGray,
                text = getTagText(context, match),
                textColor = if (match.isLive()) Color.White else Color.LightGray,
            )
        }
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            // Header with live indicator and time
            Spacer(modifier = Modifier.height(16.dp))

            // Teams section
            MatchHeader(modifier = Modifier, match, showMatchDate = false)

            // add a white line
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.3f),
            )
            Spacer(modifier = Modifier.height(8.dp))
            // League info
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    modifier =
                        Modifier
                            .size(16.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = CircleShape,
                            ),
                    model = match.leagueLogo,
                    contentDescription = "${match.description} logo",
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = match.description,
                    color = ExtendedColors.Default.textPrimary,
                    fontSize = 12.sp,
                )
            }
        }
    }
}

private fun getTagText(context: Context, match: Match): String {
    return if (match.status.equals(MatchStatus.FINISHED)) {
        context.getString(R.string.finished)
    } else {
        getStartTime(context, match.startTime)
    }
}

private fun getStartTime(
    context: Context,
    beginAt: String?,
): String {
    val now = LocalDateTime.now()
    return beginAt?.toLocalDate()?.let { startTime ->
        when {
            now.isAfter(startTime) -> context.getString(R.string.now)
            startTime.isBefore(now.plusDays(1)) -> {
                context.getString(R.string.today) + ", " + startTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            }

            else ->
                startTime
                    .format(DateTimeFormatter.ofPattern("EEE, HH:mm"))
                    .replace(".", "")
                    .replaceFirstChar { it.uppercase() }
        }
    } ?: ""
}


@Preview(showBackground = true)
@Composable
fun PreviewMatchCard() {
    val match =
        Match(
            id = 1,
            firstTeam = Team(id = 1, name = "Team A", iconUrl = "https://example.com/team_a.png"),
            secondTeam = Team(id = 2, name = "Team B", iconUrl = "https://example.com/team_b.png"),
            startTime = "2025-07-13T15:21:32Z",
            description = "League Match",
            status = MatchStatus.RUNNING,
            leagueLogo = "https://example.com/league_logo.png",
        )
    MatchCard(
        match = match,
        onClick = {},
    )
}
