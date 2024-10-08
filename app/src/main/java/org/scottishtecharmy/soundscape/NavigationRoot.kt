package org.scottishtecharmy.soundscape

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.scottishtecharmy.soundscape.screens.Home
import org.scottishtecharmy.soundscape.screens.MainScreens
import org.scottishtecharmy.soundscape.screens.markers_routes.navigation.MarkersAndRoutesNavGraph
import org.scottishtecharmy.soundscape.screens.markers_routes.navigation.ScreensForMarkersAndRoutes
import org.scottishtecharmy.soundscape.screens.markers_routes.screens.AddRouteScreen
import org.scottishtecharmy.soundscape.screens.markers_routes.screens.MarkersAndRoutesScreen

@Composable
fun NavigationRoot(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreens.Home.route
    ) {
        // Main navigation
        composable(MainScreens.Home.route) {
            Home(navController = navController)
        }

        // MarkersAndRoutesScreen with tab selection
        navigation(
            startDestination = "${MainScreens.MarkersAndRoutes.route}/{tab}",
            route = MainScreens.MarkersAndRoutes.route
        ) {
            composable("${MainScreens.MarkersAndRoutes.route}/{tab}") { backStackEntry ->
                val selectedTab = backStackEntry.arguments?.getString("tab")
                MarkersAndRoutesScreen(navController = navController, selectedTab = selectedTab)
            }

            // Nested graph: Routes and Markers
            composable(ScreensForMarkersAndRoutes.Markers.route)
            { MarkersAndRoutesNavGraph(
                navController = navController,
                startDestination = "Markers"
            )
            }
        }

        // AddRouteScreen, accessible within the MarkersAndRoutesScreen
        composable(MainScreens.AddRoute.route) {
            AddRouteScreen(navController = navController)
        }
    }
}