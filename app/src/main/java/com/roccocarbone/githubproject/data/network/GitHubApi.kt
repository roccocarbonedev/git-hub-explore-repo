package com.roccocarbone.githubproject.data.network

import com.roccocarbone.githubproject.data.models.Observer
import com.roccocarbone.githubproject.data.models.Repository
import com.roccocarbone.githubproject.data.models.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): RepositoryResponse

    @GET("repos/{owner}/{repo}/subscribers")
    suspend fun getObservers(@Path("owner") owner: String, @Path("repo") repo: String): List<Observer>
}
