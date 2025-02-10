package com.roccocarbone.githubproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roccocarbone.githubproject.data.models.Repository
import com.roccocarbone.githubproject.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Repository>>(emptyList())
    val searchResults: StateFlow<List<Repository>> = _searchResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun searchRepositories(query: String) {
        if (query.isBlank()) return

        _isLoading.value = true
        _errorMessage.value = null
        _searchResults.value = emptyList()

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.searchRepositories(query)
                _searchResults.value = response.items
            } catch (e: Exception) {
                _errorMessage.value = "Errore: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
