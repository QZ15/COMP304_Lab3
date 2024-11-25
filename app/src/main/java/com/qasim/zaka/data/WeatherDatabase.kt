package com.qasim.zaka.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}
