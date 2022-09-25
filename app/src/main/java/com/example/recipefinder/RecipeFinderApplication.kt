package com.example.recipefinder

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipeFinderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}