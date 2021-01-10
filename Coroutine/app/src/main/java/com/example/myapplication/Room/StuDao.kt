package com.example.myapplication.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stu:Student)

    @Query("select * from Student")
    suspend fun getA():Student
}