package com.example.recyclerview.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ItemListBinding

class MyAdapter(private val myDataSet: MutableList<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.item_list, viewGroup, false)
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemListBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your viewModel.todos.value!! at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = myDataSet[position]
        
        // menghapus
        viewHolder.delete.setOnClickListener{
            myDataSet.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, myDataSet.size)
        }

        // mengupdate
        viewHolder.edit.setOnClickListener {
            val context = viewHolder.itemView.context

            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.item_edit, null)

            // mengambil data sebelumnya
            val prevText = myDataSet[position]
            val editTexts = view.findViewById<TextView>(R.id.editText)
            editTexts.text = prevText

            // dilaog
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Item")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener{
                    dialog, id ->

                    //edit
                    myDataSet[position] = editTexts.text.toString()
                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, id ->

                })

            alertDialog.create().show()
        }
    }

    // Return the size of your viewModel.todos.value!! (invoked by the layout manager)
    override fun getItemCount(): Int {
        return myDataSet.size
//        return viewModel.todos.value!!.size
    }

    class ViewHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView: TextView
        val delete: Button
        val edit: Button

        init {
            // Define click listener for the ViewHolder's View.
            textView = binding.todoItem
            delete = binding.deleteBtn
            edit = binding.editBtn
        }
    }

}
