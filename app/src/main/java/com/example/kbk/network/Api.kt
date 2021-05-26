package com.example.kbk.network

import com.example.kbk.model.Dashboards
import com.example.kbk.model.GradeBooks
import com.example.kbk.model.KBKEvents
import com.example.kbk.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("login.php")
    fun userlogin (
            @Field("username") username: String,
            @Field("password") password: String
    ):Call<LoginResponse>

    @GET("allEvents.php")
    fun allevents():Call<KBKEvents>


    @GET("dashboard.php")
    fun dashboardFun (
        @Query("date") date: String,
        @Query("group") group: Int
    ):Call<Dashboards>

    @GET("dashboard2.php")
    fun dashboard2Fun (
        @Query("date") date: String,
        @Query("idu") idu: Int
    ):Call<Dashboards>

    @GET("gradebook.php")
    fun gradebookFun (
        @Query("ids") ids: Int,
        @Query("sem") sem: Int
    ):Call<GradeBooks>
}