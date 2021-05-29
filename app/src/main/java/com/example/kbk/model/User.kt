package com.example.kbk.model

import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("id")
        val id: Int,
        val auth: String,
        val confirmed: Int,
        val policyagreed: Int,
        val deleted: Int,
        val suspended: Int,
        val mnethostid: Int,
        val username: String,
        val password: String,
        val idnumber: String,
        @SerializedName("firstname")
        val firstname:String,
        @SerializedName("lastname")
        val lastname: String,
        val email: String,
        val emailstop: Int,
        @SerializedName("id_group")
        val id_group: Int,
        @SerializedName("shortname")
        val shortname:String,
        @SerializedName("year_group")
        val year_group:Int,
        @SerializedName("num_course")
        val num_course:Int,
        val icq: String,
        val skype: String,
        val yahoo: String,
        val aim: String,
        val msn: String,
        val phone1: String,
        val phone2: String,
        val institution: String,
        val department: String,
        val address: String,
        val city: String,
        val country: String,
        val lang: String,
        val calendartype: String,
        val theme: String,
        val timezone: String,
        val firstaccess: Int,
        val lastaccess: Int,
        val lastlogin: Int,
        val currentlogin: Int,
        val lastip: String,
        val secret: String,
        val picture: Int,
        val url: String,
        val descrition: String,
        val descriptionformat: Int,
        val mailformat: Int,
        val maildigest: Int,
        val maildisplay: Int,
        val autosubscribe: Int,
        val trackforums: Int,
        val timecreated: Int,
        val timemodified: Int,
        val trustbitmask: Int,
        val imagealt: String,
        val lastnamephonetic: String,
        val firstnamephonetic: String,
        val middlename: String,
        val alternatename: String
        )
{

/*        constructor(id: Int, username: String) : this() {
                this.username = username;
        }*/

}