package com.hk.trendingrepos.source

import com.hk.trendingrepos.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoRemoteSource {
    suspend fun getRepositories(): ResponseState<ArrayList<Repository>> {
        val retrofit = RetrofitBuilder.getInstance()
        val apiService: RepositoryEndpoints = retrofit.create(RepositoryEndpoints::class.java)
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getRepositories().execute()
                if (response.isSuccessful) {
                    response.body()?.repositories?.let {
                        return@withContext ResponseState.success(it.toCollection(ArrayList()))
                    }
                    return@withContext ResponseState.error(response.message(), ResponseState.ErrorType.UNKNOWN_ERR)
                } else
                    return@withContext ResponseState.error(response.message(), ResponseState.ErrorType.UNKNOWN_ERR)
            } catch (e: Exception) {
                return@withContext ResponseState.error(
                    "Unknown Error Occurred",
                    ResponseState.ErrorType.UNKNOWN_ERR
                )
            }
        }
    }
}