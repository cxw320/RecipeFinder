package com.example.myrecipe2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyRecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}