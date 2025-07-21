package com.orafaelsc.cstvfuze.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orafaelsc.cstvfuze.ui.matchdetails.MatchesDetailsScreen
import com.orafaelsc.cstvfuze.ui.matches.MatchesScreen
import com.orafaelsc.cstvfuze.ui.splash.SplashScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val matchSharedViewModel: MatchSharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash",
    ) {
        composable("splash") {
            SplashScreen(
                modifier = modifier,
                onNavigateToMatches = {
                    navController.navigate("matches") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
            )
        }

        composable("matches") {
            MatchesScreen(
                modifier = modifier,
                onMatchClick = { match ->
                    matchSharedViewModel.selectMatch(match)
                    navController.navigate("match_details")
                },
            )
        }

        composable("match_details") {
            val selectedMatch by matchSharedViewModel.selectedMatch.collectAsState()

            selectedMatch?.let { match ->
                MatchesDetailsScreen(
                    modifier = modifier,
                    match = match,
                    onBackClick = {
                        matchSharedViewModel.clearSelectedMatch()
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
