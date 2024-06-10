package com.example.tfg.Retrofit

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "session_pref"
    private const val KEY_LOGGED_IN = "logged_in"
    private const val KEY_EMAIL = "email"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_NOMBRE = "nombre"


    fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_LOGGED_IN, false)
    }

    fun setLoggedIn(context: Context, loggedIn: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_LOGGED_IN, loggedIn)
        editor.apply()
    }
    fun setEmail(context: Context, email: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(KEY_EMAIL, email)
            apply()
        }
    }

    fun getEmail(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        //return sharedPreferences.getString(KEY_USERNAME, "") ?: ""
        return sharedPreferences.getString(KEY_EMAIL, "") ?: ""
    }

    fun setUserName(context: Context, userName: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(KEY_USER_NAME, userName)
            apply()
        }
    }

    fun getUserName(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USER_NAME, "") ?: ""
    }





    fun clear(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove(KEY_LOGGED_IN)
            remove(KEY_EMAIL)
            remove(KEY_USER_NAME)
            apply()
        }
    }
}