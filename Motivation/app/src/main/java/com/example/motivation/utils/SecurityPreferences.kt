package com.example.motivation.utils

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context){
    private val SharedPreferences: SharedPreferences =    context.getSharedPreferences("motivaton", Context.MODE_PRIVATE)

    fun storeString(key:String, value: String){
        SharedPreferences.edit().putString(key,value).apply()
    }

    fun getSttorageString(key: String): String? {
        return SharedPreferences.getString(key, "")
    }
}