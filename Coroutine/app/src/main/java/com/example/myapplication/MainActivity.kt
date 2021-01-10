package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Room.StuDB
import com.example.myapplication.Room.Student
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    lateinit var db: StuDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var name: String? = null
        var age: String? = null
        db = StuDB.getInstance(this)!!
        btn.setOnClickListener {
            name = edt_1.text.toString()
            age = edt_2.text.toString()
            insert(Student(name?:"default", age?.toInt()?:0))
        }
        btn_load.setOnClickListener { load() }

    }

    fun insert(stu: Student) {
        CoroutineScope(Dispatchers.IO).launch{
            db.getDao().insert(stu)
        }
    }

    fun load() {
        lateinit var stu:Student
        runBlocking {
            stu = db.getDao().getA()
        }
        tv.text = stu.toString()
    }
}