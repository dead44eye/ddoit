package com.example.doit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class TodoItem(val title: String, var isDone: Boolean = false)

class TodoAdapter(private val todoItems: MutableList<TodoItem>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var onItemClickListener: ((Int) -> Unit)? = null

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTodoTitle: TextView = itemView.findViewById(R.id.tvTodoTitle)
        val cbDone: CheckBox = itemView.findViewById(R.id.cbDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = todoItems[position]
        holder.tvTodoTitle.text = currentItem.title

        // Imposta lo stato del CheckBox
        holder.cbDone.isChecked = currentItem.isDone

        // Aggiungi il listener alla CheckBox
        holder.cbDone.setOnClickListener {
            currentItem.isDone = !currentItem.isDone
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = todoItems.size
}
