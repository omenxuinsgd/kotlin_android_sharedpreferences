package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.navigation.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {
    private lateinit var binding: FragmentFinishBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "End Game"
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_finish, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finish, container, false)
        val args = FinishFragmentArgs.fromBundle(requireArguments())
        binding.resultTxt.text = args.result
        return binding.root
    }

}