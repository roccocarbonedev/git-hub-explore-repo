package com.roccocarbone.githubproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.roccocarbone.githubproject.ui.screens.ObserversPage
import com.roccocarbone.githubproject.ui.screens.SearchPage

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object Observers : Screen("observers/{owner}/{repo}") {
        fun createRoute(owner: String, repo: String): String = "observers/$owner/$repo"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            SearchPage(onRepositorySelected = { repository ->
                navController.navigate(Screen.Observers.createRoute(repository.owner.login, repository.name))
            })
        }
        composable(Screen.Observers.route) { backStackEntry ->
            val owner = backStackEntry.arguments?.getString("owner") ?: return@composable
            val repo = backStackEntry.arguments?.getString("repo") ?: return@composable
            ObserversPage(owner, repo)
        }
    }
}

