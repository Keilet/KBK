package com.example.kbk

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RetrofitClient"

// вариант 1 Simplified Coding Retrofit Android Tutorial - Sign Up using Retrofit POST Request
class RetrofitClient {
    private val BASE_URL: String = "https://kbkapp.000webhostapp.com/"
/*    private val service: Api

    *//*    private lateinit var mInstance: RetrofitClient
        private lateinit var retrofit: Retrofit*//*
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(Api::class.java)
    }

    fun fetchContents(): LiveData<LoginResponse> {
        val responseLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
        val call: Call<LoginResponse> = service.fetchContents()
        call.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d(TAG, "Response received")
                responseLiveData.value = response.body()
            }
        })
        return responseLiveData
    }
}*/


/*    private fun RetrofitClient(){
        retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }*/

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