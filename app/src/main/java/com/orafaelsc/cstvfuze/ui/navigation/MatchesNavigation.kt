package com.orafaelsc.cstvfuze.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orafaelsc.cstvfuze.ui.matches.MatchesScreen

@Composable
fun MatchesNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "matches"
    ) {
        composable("matches") {
            MatchesScreen(
                onMatchClick = { match ->
                    navController.navigate("match_details/${match.id}")
                }
            )
        }
    }
}
