package com.roccocarbone.githubproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.roccocarbone.githubproject.data.models.Repository
import com.roccocarbone.githubproject.viewmodel.SearchViewModel

@Composable
fun SearchPage(
    onRepositorySelected: (Repository) -> Unit
) {
    val searchViewModel: SearchViewModel = viewModel()
    val searchResults by searchViewModel.searchResults.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()
    val errorMessage by searchViewModel.errorMessage.collectAsState()
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Cerca Repository") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { searchViewModel.searchRepositories(searchText.text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerca")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.width(20.dp),
            )
        }

        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        LazyColumn {
            items(searchResults) { repo ->
                RepositoryItem(repo, onRepositorySelected)
            }
        }
    }
}

@Composable
fun RepositoryItem(repository: Repository, onRepositorySelected: (Repository) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onRepositorySelected(repository) }

    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = rememberAsyncImagePainter(repository.owner.avatarUrl),
                contentDescription = "Avatar di ${repository.owner.login}",
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(text = repository.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "Owner: ${repository.owner.login}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPagePreview() {
    SearchPage(onRepositorySelected = {})
}
