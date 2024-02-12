package com.example.a5000wordsbookquiz.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.a5000wordsbookquiz.R
import com.example.a5000wordsbookquiz.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)

    }

    private fun bindView(view: View) {
        _binding = FragmentQuestionBinding.bind(view)
    }

    private fun unBindView() {
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindView()
    }
}