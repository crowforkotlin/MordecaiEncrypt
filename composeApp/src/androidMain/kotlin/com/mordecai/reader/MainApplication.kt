package com.mordecai.reader

import android.app.Application
import com.mordecai.reader.model.di.networkModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                networkModule
            )
        }
    }
}