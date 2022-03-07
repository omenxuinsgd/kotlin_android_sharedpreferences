package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navigation.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    var result = ""
    var current_q = 0
    var score_tele = 0
    var score_ctrl = 0

    val questions = arrayOf("Apa Yang Kamu Paling Kamu Minati?",
                            "Selama ini Kamu Nyaman Mengerjakan Project Apa?",
                            "Setelah Lulus Kamu Ingin Bekerja di Bidang Apa?")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz Konsentrasi"
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_quiz, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
//        binding.Q1.text = questions[0]

        binding.ctrlBtn.setOnClickListener{
            score_ctrl++
            nextQuestions(binding)
        }

        binding.teleBtn.setOnClickListener{
            score_tele++
            nextQuestions(binding)
        }

        return binding.root
    }

    fun nextQuestions(binding: FragmentQuizBinding){
       current_q++
       if(current_q < questions.size) {
           binding.Q1.text = questions[current_q]
           Toast.makeText(
               context, "berhasil, tele: " + score_tele + ", ctrl: " + score_ctrl,
               Toast.LENGTH_SHORT
           ).show()
       }else{
           Toast.makeText(
               context, "sudah maksimal.. next fragment",
               Toast.LENGTH_SHORT
           ).show()

           if(score_tele >=1 && score_ctrl >=1){
               result = "anda minat di Telekomunikasi dan Sistem Kontrol"
           }else if(score_tele<1){
               result = "anda minat di Sistem Kontrol, anda harus masuk kesitu"
           }else{
               result = "anda minat di Telekomunikasi, anda harus masuk kesitu"
           }
           requireView().findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToFinishFragment(result))

           }
    }
}