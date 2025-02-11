package com.roccocarbone.githubproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.roccocarbone.githubproject.data.models.Observer
import com.roccocarbone.githubproject.viewmodel.ObserversViewModel

@Composable
fun ObserversPage(owner: String, repo: String, viewModel: ObserversViewModel = viewModel()) {
    val observers by viewModel.observers.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadObservers(owner, repo)
    }

    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(20.dp),
        )
    }

    errorMessage?.let {
        Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
    }


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Osservatori di ${repo}", style = MaterialTheme.typography.titleLarge)

        LazyColumn {
            items(observers) { observer ->
                ObserverItem(observer)
            }
        }
    }
}

@Composable
fun ObserverItem(observer: Observer) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        //TODO: Add click and open image to dialog
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(observer.avatarUrl),
                contentDescription = "Avatar di ${observer.login}",
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = observer.login, style = MaterialTheme.typography.titleMedium)
        }
    }
}
