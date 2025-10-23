package com.example.ozinshe20.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedProvider(private val context: Context) {
    private val shared_token = "SAVE_TOKEN"

    private val preference:SharedPreferences
        get() = context.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        preference.edit { putString(shared_token, token) }
    }

    fun getToken(): String {
        return preference.getString(shared_token, "without_token").toString()
    }


}