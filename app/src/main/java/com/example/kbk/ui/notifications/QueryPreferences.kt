package com.example.kbk.ui.notifications

import android.content.Context
import android.provider.Settings.Global.putString
import androidx.preference.PreferenceManager

private const val PREF_LAST_RESULT_ID =
    "lastResultId"

object QueryPreferences {
    fun getLastResultId(context: Context):String{
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_LAST_RESULT_ID,"")!!
    }
    fun setLastResultId(context: Context,lastResultId: String) {
/*        PreferenceManager.getDefaultSharedPreferences(context).edit
        {
            putString(
                PREF_LAST_RESULT_ID,
                lastResultId
            )
        }*/
    }
}