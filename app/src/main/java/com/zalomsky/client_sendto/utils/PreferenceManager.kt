package com.zalomsky.client_sendto.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager@Inject constructor(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }
}