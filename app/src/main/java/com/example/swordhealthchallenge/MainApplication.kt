package com.example.swordhealthchallenge

import android.app.Application
import com.example.swordhealthchallenge.di.AppComponent
import com.example.swordhealthchallenge.di.DaggerAppComponent

class MainApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
