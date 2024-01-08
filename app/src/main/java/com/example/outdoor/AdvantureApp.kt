package com.example.outdoor

import android.app.Application
import com.example.outdoor.repositori.DataContainer

class AdvantureApp : Application(){
    lateinit var dataContainer: DataContainer

    override fun onCreate() {
        super.onCreate()
        dataContainer = DataContainer(this)
    }
}