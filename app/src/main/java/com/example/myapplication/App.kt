package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.AppDatabase

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "simple-database"
        ).build()
    }
}