package com.archrahkshi.civrandomizer

import android.app.Application
import androidx.room.Room
import com.archrahkshi.civrandomizer.data.AppDatabase

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "civDatabase"
        ).build()
    }
}