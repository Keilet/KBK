package com.example.kbk.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// вариант 3 Simplified Coding Android Login/Signup with MVVM - Retrofit
class RemoteDataSource {
    companion object {
        private const val BASE_URL = "https://kbkapp.000webhostapp.com/"
    }

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(api)
    }
}