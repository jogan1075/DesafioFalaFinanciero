package com.jmc.tecnicaltestfifjmc

import android.app.Application
import android.content.Context
import com.jmc.tecnicaltestfifjmc.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        startKoin {
            androidContext(this@App)
            modules(
                appModule
            )
        }

    }


    companion object {
        lateinit var context: Context
    }
}