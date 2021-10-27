package com.hk.trendingrepos.source

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private const val BASE_URL = "https://api.github.com/"
    private var INSTANCE: Retrofit? = null

    fun getInstance(): Retrofit {
        if (INSTANCE == null)
            INSTANCE =
                Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        return INSTANCE!!
    }
}