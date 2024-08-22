package com.crow.mordecai.encrypt

import android.annotation.SuppressLint
import android.app.Application
import java.util.logging.Logger

val app = BaseApp.context

open class BaseApp : Application() {

    companion object { lateinit var context: Application }

    @SuppressLint("RestrictedApi")
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}