package com.example.kbk

import android.content.Context
import android.content.SharedPreferences




class SharedPrefManager {
    private var mInstance: SharedPrefManager? = null
    private var mCtx: Context? = null

    private val SHARED_PREF_NAME = "sharedprefretrofit"

    private val KEY_USER_ID = "keyuserid"
    private val KEY_USER_NAME = "keyusername"
    private val KEY_USER_EMAIL = "keyuseremail"
    private val KEY_USER_GENDER = "keyusergender"

/*    private fun SharedPrefManager(context: Context): SharedPrefManager? {
        mCtx = context
    }

    @Synchronized
    fun getInstance(context: Context): SharedPrefManager? {
        if (mInstance == null) {
            mInstance = SharedPrefManager(context)
        }
        return mInstance
    }*/

    fun shuserLogin(user: User): Boolean {
        val sharedPreferences: SharedPreferences = mCtx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_USER_ID, user.id)
        editor.putString(KEY_USER_NAME, user.username)
        editor.putString(KEY_USER_EMAIL, user.email)
        editor.apply()
        return true
    }

    fun isLoggedIn(): Boolean {
        val sharedPreferences: SharedPreferences = mCtx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return if (sharedPreferences.getString(KEY_USER_EMAIL, null) != null) true else false
    }

/*    fun getUser(): User {
        val sharedPreferences: SharedPreferences = mCtx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return User(
                sharedPreferences.getInt(KEY_USER_ID, 0),
                sharedPreferences.getString(KEY_USER_NAME, null)!!,
                sharedPreferences.getString(KEY_USER_EMAIL, null),
        )
    }*/

    fun logout(): Boolean {
        val sharedPreferences: SharedPreferences = mCtx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        return true
    }
}