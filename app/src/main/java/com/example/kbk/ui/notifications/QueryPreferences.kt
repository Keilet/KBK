package com.example.kbk.ui.notifications

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.core.content.edit

private const val PREF_SEARCH_QUERY = "searchQuery"
private const val PREF_LAST_RESULT_ID ="lastResultId"

object QueryPreferences {

    fun getStoredQuery(context: Context): String {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PREF_SEARCH_QUERY, "")!!
    }

    fun setStoredQuery(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit {
                putString(PREF_SEARCH_QUERY, query)
            }
    }

    fun getLastResultId(context: Context):Int{
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(PREF_LAST_RESULT_ID,0)!!
    }

    fun setLastResultId(context: Context,lastResultId: Int) {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putInt(PREF_LAST_RESULT_ID, lastResultId)
        }
    }
}