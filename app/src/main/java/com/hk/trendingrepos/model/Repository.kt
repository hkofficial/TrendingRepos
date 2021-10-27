package com.hk.trendingrepos.model

import com.google.gson.annotations.SerializedName


data class Repository(
    @SerializedName("full_name")
    val repositoryName: String,
    @SerializedName("description")
    val repositoryDescription: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("stargazers_count")
    val starCount: Int = 0,
    @SerializedName("owner")
    val repoOwner: RepositoryOwner
)
