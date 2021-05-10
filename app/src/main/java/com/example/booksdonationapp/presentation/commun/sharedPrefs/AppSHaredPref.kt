package com.example.booksdonationapp.presentation.commun.sharedPrefs

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.GsonBuilder

const val USER = "user"
const val IS_CONNECTED = "is_connected"
const val NIGHT_THEME_PREFERENCE = "night_theme_preference"

class AppSHaredPref(context: Context) {

    private val gson by lazy { GsonBuilder().create() }


    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val pref = EncryptedSharedPreferences.create(
        "ENCRYPTED_PREF_FILE_NAME",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var nightThemePref: Boolean
        get() = pref.getBoolean(NIGHT_THEME_PREFERENCE, false)
        set(value) = pref.edit().putBoolean(NIGHT_THEME_PREFERENCE, value).apply()


}