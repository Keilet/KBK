package com.example.kbk

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
    //@Query("id")id:Int

}