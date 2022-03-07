package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var textView = binding.text
        var editText = binding.editText
        var button = binding.button

        sharedPref = getPreferences(Context.MODE_PRIVATE)

        // load text
        textView.text = load("Data")

        // submit button
        button.setOnClickListener{
            val text = editText.text.toString()
            textView.text = text

            // save sharedpreferences
            save(text)
        }
    }

    private fun save(text: String){
        with(sharedPref.edit()){
            putString("Data", text)
            commit()
        }
    }

    private fun load(key: String): String{
        return sharedPref.getString(key, "Tampilan Awal").toString()
    }
}