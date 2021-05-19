package com.example.kbk.db;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kbk.model.Dashboard

@Database(entities =[Dashboard::class], version = 1,exportSchema = false)
abstract class DashboardDatabase:RoomDatabase() {
    abstract fun getDashboardDao(): DashboardDao

    companion object {
        @Volatile
        private var instance: DashboardDatabase? = null
        private val LOCK = Any()

        //elvis operator
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DashboardDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}
