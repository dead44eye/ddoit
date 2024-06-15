package com.example.doit

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoList: ArrayList<TodoItem>
    private lateinit var adapter: TodoAdapter
    private lateinit var rvTodoItems: RecyclerView
    private lateinit var etTodoTitle: EditText
    private lateinit var btnAddTodo: ImageButton
    private lateinit var btnDeleteDoneTodolist: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoList = ArrayList()
        adapter = TodoAdapter(todoList)

        rvTodoItems = findViewById(R.id.rvTodoitems)
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        rvTodoItems.adapter = adapter

        etTodoTitle = findViewById(R.id.etTodoTitle)
        btnAddTodo = findViewById(R.id.btnAddTodo)
        btnDeleteDoneTodolist = findViewById(R.id.btnDeleteDoneTodolist)

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                todoList.add(TodoItem(todoTitle))
                adapter.notifyItemInserted(todoList.size - 1)
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDoneTodolist.setOnClickListener {
            for (i in todoList.size - 1 downTo 0) {
                if (todoList[i].isDone) {
                    todoList.removeAt(i)
                    adapter.notifyItemRemoved(i)
                }
            }
        }

        adapter.onItemClickListener = { position ->
            val clickedItem = todoList[position]
            clickedItem.isDone = !clickedItem.isDone
            Log.d("MainActivity", "Item clicked: ${clickedItem.title} is now ${clickedItem.isDone}")
            adapter.notifyItemChanged(position)
        }
    }
}
