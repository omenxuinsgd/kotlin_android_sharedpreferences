package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.adapter.MyAdapter
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    //private lateinit var viewModel: TodoViewModel

    val todos = mutableListOf(
            "bangun", "sikat gigi", "mandi",
            "todo 4", "todo 5", "todo 6",
            "todo 7", "todo 8", "mandi"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewAdapter = MyAdapter(todos)
        viewManager = LinearLayoutManager(this)

        recyclerView = binding.myRecyclview
        val newBtn = binding.newBtn

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        newBtn.setOnClickListener{
            todos.add(binding.newText.text.toString())
        }
    }
}