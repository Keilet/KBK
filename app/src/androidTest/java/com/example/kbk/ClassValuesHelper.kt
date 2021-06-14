package com.example.kbk

import android.content.SharedPreferences
import android.util.Log
import com.example.kbk.model.LoginResponse
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClassValuesHelper{
    val KEY_FIRST_OPERAND = "login"
    val KEY_SECOND_OPERAND = "ids"

    private var sharedPreferences: SharedPreferences? = null

    constructor(sharedPreferences: SharedPreferences?) {
        this.sharedPreferences = sharedPreferences
    }

//    fun saveValues() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(ServiceBuilder.URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val service: Api = retrofit.create(Api::class.java)
//        var call: Call<LoginResponse> = service.userloginstudent("kea", "12345")
//        call.enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (!response.body()!!.error) {
//                    sharedPreferences!!.edit()
//                        .putString(KEY_FIRST_OPERAND, response.body()!!.user.username)
//                        .putInt(KEY_SECOND_OPERAND, response.body()!!.user.id)
//                        .commit()
//                }
//                else
//                {
//                    Log.d("message",response.body()!!.error.toString())
//                }
//            }
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                Log.d("message",t.message.toString())
//            }
//        })
//    }
    fun readValues(): Values? {
        val values = Values()
        values.setFirstOperand(sharedPreferences?.getString(KEY_FIRST_OPERAND,"").toString())
        sharedPreferences?.let { values.setSecondOperand(it.getInt(KEY_SECOND_OPERAND,0)) }
        return values
    }
}