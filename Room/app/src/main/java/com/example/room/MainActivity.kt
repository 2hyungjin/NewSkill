package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.room.DB
import com.example.room.room.Entity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val db = DB.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_insert.setOnClickListener { db?.getDao()?.insert(Entity(edt.text.toString())) }
        btn_select.setOnClickListener { db?.getDao()?.select().let {
            textView.text=it.toString()
        } }
    }

}