package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.ui.theme.ExtendedColors
import java.time.LocalDateTime

@Composable
fun MatchHeader(
    modifier: Modifier = Modifier,
    match: Match,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
        ) {
            TeamItem(teamName = match.firstTeam.name, teamLogoUrl = match.firstTeam.iconUrl)
        }

        Text(
            text = stringResource(R.string.vs),
            color = ExtendedColors.Default.textSecondary,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
        ) {
            TeamItem(teamName = match.secondTeam.name, teamLogoUrl = match.secondTeam.iconUrl)
        }
    }
}

@Preview
@Composable
fun MatchHeaderPreview() {
    MatchHeader(
        match =
            Match(
                id = 1,
                firstTeam =
                    com.orafaelsc.cstvfuze.domain.model.Team(
                        id = 1,
                        name = "Team A",
                        iconUrl = "https://example.com/team_a_logo.png",
                    ),
                secondTeam =
                    com.orafaelsc.cstvfuze.domain.model.Team(
                        id = 2,
                        name = "Team B",
                        iconUrl = "https://example.com/team_b_logo.png",
                    ),
                startTime = LocalDateTime.now().toString(),
                description = "Match between Team A and Team B",
                leagueLogo = "https://example.com/league_logo.png",
                status = MatchStatus.RUNNING,
            ),
    )
}
