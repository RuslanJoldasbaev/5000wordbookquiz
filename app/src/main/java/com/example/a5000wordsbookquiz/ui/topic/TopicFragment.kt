package com.example.a5000wordsbookquiz.ui.topic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.a5000wordsbookquiz.R
import com.example.a5000wordsbookquiz.databinding.FragmentTopicBinding
import com.example.a5000wordsbookquiz.presentation.QuestionsViewModel
import com.example.a5000wordsbookquiz.presentation.TopicsViewModel
import com.example.a5000wordsbookquiz.ui.adapters.QuestionAdapter
import com.example.a5000wordsbookquiz.ui.adapters.TopicsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TopicFragment : Fragment(R.layout.fragment_topic) {

    private var _binding: FragmentTopicBinding? = null
    private val binding get() = _binding!!

    private val adapter = TopicsAdapter()
    private lateinit var viewModel: TopicsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[TopicsViewModel::class.java]


        initObservers()

        lifecycleScope.launch {
            viewModel.getAllTopics()
        }

        binding.recyclerView.adapter = adapter

        adapter.setOnItemClickListener { id, position ->
            findNavController().navigate(
                TopicFragmentDirections.actionTopicFragmentToSectionFragment(id)
            )
        }
    }

    private fun initObservers() {
        viewModel.getAllTopicFlow.onEach {
            adapter.models = it.toMutableList()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.messageFlow.onEach {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun bindView(view: View) {
        _binding = FragmentTopicBinding.bind(view)
    }

    private fun unBindView() {
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        unBindView()
    }
}