package com.spavv.m.di

import android.app.Application

class MyApp : Application() {
    companion object {
        lateinit var  appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}