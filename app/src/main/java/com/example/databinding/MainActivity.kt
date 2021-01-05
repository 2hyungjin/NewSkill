package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var text:String="hello world!"
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.main=this
    }

    fun clicked(){
        text="btn is clicked"
        Toast.makeText(this, "btn is clicked!", Toast.LENGTH_SHORT).show()
        binding.tvTitle.text=text
    }
}