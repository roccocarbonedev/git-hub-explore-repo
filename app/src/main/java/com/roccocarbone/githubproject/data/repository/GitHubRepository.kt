package com.roccocarbone.githubproject.data.repository


import com.roccocarbone.githubproject.data.models.Repository
import com.roccocarbone.githubproject.data.models.Observer
import com.roccocarbone.githubproject.data.network.RetrofitClient

class GithubRepository {
    private val api = RetrofitClient.api

    suspend fun searchRepositories(query: String): List<Repository> {
        return api.searchRepositories(query).items
    }

    suspend fun getObservers(owner: String, repo: String): List<Observer> {
        return api.getObservers(owner, repo)
    }
}
