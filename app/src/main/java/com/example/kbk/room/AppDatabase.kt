package com.example.kbk.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Rdashboard::class), version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun dashbDao(): RdashboardDao
}
