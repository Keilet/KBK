package com.example.kbk.model

import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("id")
        val id: Int,
        val deleted: Int,
        val mnethostid: Int,
        val username: String,
        val password: String,
        @SerializedName("firstname")
        val firstname:String,
        @SerializedName("lastname")
        val lastname: String,
        val email: String,
        @SerializedName("id_group")
        val id_group: Int,
        @SerializedName("shortname")
        val shortname:String,
        @SerializedName("year_group")
        val year_group:Int,
        @SerializedName("num_course")
        val num_course:Int,
        val phone1: String,
        val picture: Int,
        val timecreated: Int,
        val timemodified: Int
        )
{

/*        constructor(id: Int, username: String) : this() {
                this.username = username;
        }*/

}