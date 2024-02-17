package com.example.a5000wordsbookquiz.ui.section

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a5000wordsbookquiz.R
import com.example.a5000wordsbookquiz.databinding.FragmentSectionBinding
import com.example.a5000wordsbookquiz.presentation.SectionViewModel
import com.example.a5000wordsbookquiz.presentation.TopicsViewModel
import com.example.a5000wordsbookquiz.ui.adapters.SectionAdapter
import com.example.a5000wordsbookquiz.ui.adapters.TopicsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SectionFragment : Fragment(R.layout.fragment_section) {
    private var _binding: FragmentSectionBinding? = null
    private val binding get() = _binding!!

    private val adapter = SectionAdapter()
    private lateinit var viewModel: SectionViewModel
    private val navArgs by navArgs<SectionFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[SectionViewModel::class.java]

        initObservers()

        lifecycleScope.launch {
            viewModel.getAllSection(navArgs.topicId)
        }

        binding.recyclerView.adapter = adapter

        adapter.setOnItemClickListener { id, position ->
            findNavController().navigate(
                SectionFragmentDirections.actionSectionFragmentToQuestionFragment(id)
            )
        }
    }

    private fun initObservers() {
        viewModel.getAllSectionFlow.onEach {
            Log.d("TTT", it.toString())
            adapter.models = it.toMutableList()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.messageFlow.onEach {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun bindView(view: View) {
        _binding = FragmentSectionBinding.bind(view)
    }

    private fun unBindView() {
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        unBindView()
    }
}