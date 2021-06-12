package com.example.kbk.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kbk.model.KBKMessages
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RetrofitClient"

class RetrofitClient {
    companion object {
        private val BASE_URL: String = "https://kbkapp.000webhostapp.com/"
    }
    private val kbkApi:Api
    init {
        val client = OkHttpClient.Builder().build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        kbkApi = retrofit.create(Api::class.java)
    }
    fun allMessegeRequest(): Call<KBKMessages> {
        return kbkApi.allmessages()
    }
}