package com.example.kbk.network

import com.example.kbk.model.Dashboards
import com.example.kbk.model.KBKEvents
import com.example.kbk.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("login.php")
    fun userlogin (   //вариант 3
            @Field("username") username: String,
            @Field("password") password: String
    ):Call<LoginResponse>

    @GET("allEvents.php")
    fun allevents():Call<KBKEvents>


    @GET("dashboard.php")
    fun dashboardFun (   //вариант 3
        @Query("date") date: String,
        @Query("group") group: Int
    ):Call<Dashboards>
}