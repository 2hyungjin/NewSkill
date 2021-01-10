package com.example.myapplication.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Student::class], version = 1)
abstract class StuDB : RoomDatabase() {
    abstract fun getDao(): StuDao

    companion object {
        private var INSTANCE: StuDB? = null

        fun getInstance(context: Context): StuDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    StuDB::class.java,
                    "db"
                ).build()
            }
            return INSTANCE
        }
    }
}