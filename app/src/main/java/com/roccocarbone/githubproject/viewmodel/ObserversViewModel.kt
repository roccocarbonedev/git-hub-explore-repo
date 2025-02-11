package com.roccocarbone.githubproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roccocarbone.githubproject.data.models.Observer
import com.roccocarbone.githubproject.data.repository.GithubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ObserversViewModel : ViewModel() {

    private val repository = GithubRepository()

    private val _observers = MutableStateFlow<List<Observer>>(emptyList())
    val observers: StateFlow<List<Observer>> = _observers

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadObservers(owner: String, repo: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                _observers.value = repository.getObservers(owner, repo)
            } catch (e: Exception) {
                _errorMessage.value = "Errore: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
