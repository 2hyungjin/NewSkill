package com.example.diffutilpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = StudentListAdapter()
        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        adapter.submitList(
            listOf(
                Student("형진", 1),
                Student("주영", 2),
                Student("수아", 3)
            )
        )
        binding.button.setOnClickListener {
            adapter.submitList(listOf(Student("길동", 5)))
        }


    }
}