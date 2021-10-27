package com.hk.trendingrepos.source

import com.hk.trendingrepos.model.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET


interface RepositoryEndpoints {
    @GET("search/repositories?q=language=+sort:stars")
    fun getRepositories(): Call<RepositoryResponse>
}