package com.example.swordhealthchallenge

import android.app.Application
import com.example.swordhealthchallenge.data.local.AppDatabase
import com.example.swordhealthchallenge.di.AppComponent
import com.example.swordhealthchallenge.di.DaggerAppComponent
import javax.inject.Inject

class MainApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}
