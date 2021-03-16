package com.example.booksdonationapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BooksDonationApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}