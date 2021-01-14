package com.example.room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class],version = 1)
abstract class DB : RoomDatabase(){
    abstract fun getDao():Dao

    companion object{
        private var INSTANCE:DB?=null
        fun getInstance(context: Context): DB? {
            if (INSTANCE==null){
                INSTANCE=Room.databaseBuilder(
                    context,
                    DB::class.java,
                    "db"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}