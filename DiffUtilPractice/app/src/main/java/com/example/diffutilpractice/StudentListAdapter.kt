package com.example.diffutilpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class StudentListAdapter : ListAdapter<Student, StudentListAdapter.ViewHolder>(StudentDiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name_rv_item)
        val tvId = view.findViewById<TextView>(R.id.tv_id_rv_item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentListAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StudentListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvName.text = item.name
        holder.tvId.text = item.id.toString()
    }
}

