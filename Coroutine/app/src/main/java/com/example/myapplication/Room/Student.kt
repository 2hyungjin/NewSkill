package com.example.myapplication.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    var name: String,
    var age: Int
){
    @PrimaryKey
    var num:Int=0
}