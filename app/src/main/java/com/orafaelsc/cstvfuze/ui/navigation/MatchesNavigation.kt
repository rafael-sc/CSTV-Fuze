package com.orafaelsc.cstvfuze.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orafaelsc.cstvfuze.ui.matches.MatchesScreen

@Composable
fun MatchesNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = "matches",
    ) {
        composable("matches") {
            MatchesScreen(
                modifier = modifier,
                onMatchClick = { match ->
                    navController.navigate("match_details/${match.id}")
                },
            )
        }
    }
}
