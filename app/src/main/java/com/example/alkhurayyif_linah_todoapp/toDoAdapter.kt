package com.example.alkhurayyif_linah_todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view.view.*

class toDoAdapter(private val tasks: ArrayList<ToDo>):
    RecyclerView.Adapter<toDoAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task = tasks[position]
    holder.itemView.apply {
        TaskRecyclerView.text = task.task
        checkBox.isChecked = task.checked
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                TaskRecyclerView.setTextColor(Color.GRAY)
            }else{
                TaskRecyclerView.setTextColor(Color.BLACK)
            }
            task.checked = !task.checked
        }
    }
    }
    override fun getItemCount() = tasks.size
    fun deleteTask(){
        tasks.removeAll{task -> task.checked }
        notifyDataSetChanged()
    }
}
