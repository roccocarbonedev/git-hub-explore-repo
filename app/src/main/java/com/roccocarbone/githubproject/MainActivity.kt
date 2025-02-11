package com.roccocarbone.githubproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.roccocarbone.githubproject.navigation.NavGraph
import com.roccocarbone.githubproject.ui.theme.GitHubProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubProjectTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
