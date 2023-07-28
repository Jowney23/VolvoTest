package com.example.volvotest

import android.app.Application
import com.example.volvotest.net.RetrofitMaster
import com.example.volvotest.net.ServerApi
import com.example.volvotest.net.ServerURL

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitMaster.getInstance().init(ServerURL.URL_BASE, ServerApi::class.java, null)
    }
}