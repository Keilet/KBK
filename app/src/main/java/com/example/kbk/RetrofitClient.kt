package com.example.kbk

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// вариант 1 Simplified Coding Retrofit Android Tutorial - Sign Up using Retrofit POST Request
class RetrofitClient {
    private val BASE_URL: String = "https://kbkapp.000webhostapp.com/"
    private lateinit var mInstance: RetrofitClient
    private lateinit var retrofit: Retrofit

    private fun RetrofitClient(){
        retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

/*    @Synchronized fun RetrofitClient getInstance(){
        if (mInstance==null){
            mInstance= RetrofitClient()
        }
        return mInstance
    }*/

/*    fun Api getApi(){
        return retrofit.create(Api.class)
    }*/
}