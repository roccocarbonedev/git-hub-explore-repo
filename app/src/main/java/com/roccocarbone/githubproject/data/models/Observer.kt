package com.roccocarbone.githubproject.data.models

import com.google.gson.annotations.SerializedName

data class Observer(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

