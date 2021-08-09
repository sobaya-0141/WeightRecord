package sobaya.app.main

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import sobaya.app.fitness_list.FitnessList
import sobaya.app.main.Screen.FitnessList
import sobaya.app.weight_list.WeightList

@Composable
fun Main() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.WeightList,
        FitnessList,
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.WeightList.route, Modifier.padding(innerPadding)) {
            composable(Screen.WeightList.route) { WeightList(navController) }
            composable(Screen.FitnessList.route) { FitnessList(navController) }
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object WeightList : Screen("weightList", sobaya.app.resources.R.string.nav_item_weight_list)
    object FitnessList : Screen("fitnessList", sobaya.app.resources.R.string.nav_item_fitness)
}