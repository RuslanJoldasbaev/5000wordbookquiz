package com.example.a5000wordsbookquiz.ui.question

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.a5000wordsbookquiz.R
import com.example.a5000wordsbookquiz.databinding.FragmentQuestionBinding
import com.example.a5000wordsbookquiz.presentation.QuestionsViewModel
import com.example.a5000wordsbookquiz.ui.adapters.QuestionAdapter
import com.example.a5000wordsbookquiz.ui.section.SectionFragmentArgs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private val adapter = QuestionAdapter()
    private lateinit var viewModel: QuestionsViewModel
    private val navArgs by navArgs<QuestionFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[QuestionsViewModel::class.java]


        initObservers()

        lifecycleScope.launch {
            viewModel.getAllQuestions()
        }

        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.getAllQuestionsFlow.onEach {
            Log.d("TTT", it.toString())
            Snackbar.make(requireView(), "Success", Snackbar.LENGTH_SHORT).show()
            adapter.models = it.toMutableList()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.messageFlow.onEach {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
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