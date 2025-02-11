package com.roccocarbone.githubproject.ui.screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.roccocarbone.githubproject.data.models.Observer
import com.roccocarbone.githubproject.viewmodel.ObserversViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObserversPage(owner: String, repo: String, viewModel: ObserversViewModel = viewModel()) {
    val observers by viewModel.observers.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher


    LaunchedEffect(Unit) {
        viewModel.loadObservers(owner, repo)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Osservatori di $repo") },
                navigationIcon = {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Torna indietro")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            errorMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
            }

            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(observers) { observer ->
                    ObserverItem(observer)
                }
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
