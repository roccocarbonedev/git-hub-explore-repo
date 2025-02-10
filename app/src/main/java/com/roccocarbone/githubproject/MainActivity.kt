package com.roccocarbone.githubproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roccocarbone.githubproject.ui.screens.SearchPage
import com.roccocarbone.githubproject.ui.theme.GitHubProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubProjectTheme {
                SearchPage()
            }
        }
    }
}
