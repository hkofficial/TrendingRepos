package com.hk.trendingrepos.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("items")
    val repositories: List<Repository>)
