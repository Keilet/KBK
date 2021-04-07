package com.example.kbk

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// вариант 2 https://dev.to/bensalcie/android-kotlin-get-data-from-restful-api-having-multiple-json-objects-o5a
object ServiceBuilder {

    private const val URL ="https://kbkapp.000webhostapp.com/"
    //CREATE HTTP CLIENT
    private val okHttp = OkHttpClient.Builder()

    //retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    //create retrofit Instance
    private val retrofit = builder.build()

    //we will use this class to create an anonymous inner class function that
    //implements Country service Interface


    fun <T> getApi ():T{
        return retrofit.create(Api.class)
    }
}