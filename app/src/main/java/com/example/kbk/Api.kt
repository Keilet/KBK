package com.example.kbk

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @GET("login")
    fun userlogin (   //вариант 3
            @Field("username") username: String,
            @Field("password") password: String
    ):Call<LoginResponse>
    //fun getUser () : Call<LoginResponse> // вариант 2
/*
    Call<LoginResponse> getUser( // вариант 1
        @Field("username") username:String,
    )
*/


}