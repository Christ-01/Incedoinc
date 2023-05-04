package com.test.incedoinc.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val baseUrl = "https://jsonplaceholder.typicode.com/"

    val incedoincApi: IncedoincApi by lazy {
        val httpClient = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(IncedoincApi::class.java)
    }
}