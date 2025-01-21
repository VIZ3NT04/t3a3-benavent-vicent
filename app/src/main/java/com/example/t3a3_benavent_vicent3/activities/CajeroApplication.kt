package com.example.t3a3_benavent_vicent3.activities

import android.app.Application
import androidx.room.Room

class CajeroApplication : Application() {

    companion object {
        lateinit var database: CajeroDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            CajeroDatabase::class.java
            , "CajeroDatabase")
            .build()

    }
}