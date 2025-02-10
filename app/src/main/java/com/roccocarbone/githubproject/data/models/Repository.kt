package com.roccocarbone.githubproject.data.models

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val items: List<Repository>
)

data class Repository(
    val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("watchers_count") val watchers: Int,
    @SerializedName("forks_count") val forks: Int,
    @SerializedName("language") val language: String?,
    val owner: Owner
)

data class Owner(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

