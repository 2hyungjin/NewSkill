package com.example.room.room

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t:Entity)
    @Query("SELECT * FROM entity")
    fun select():Entity
}