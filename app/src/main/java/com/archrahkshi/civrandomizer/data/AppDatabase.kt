package com.archrahkshi.civrandomizer.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Civ::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun civsDao(): CivsDao
}