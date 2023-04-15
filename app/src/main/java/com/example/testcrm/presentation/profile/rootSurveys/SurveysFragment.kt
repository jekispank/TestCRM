package com.example.testcrm.presentation.profile.rootSurveys

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testcrm.databinding.FragmentSurveysBinding
import com.example.testcrm.presentation.profile.Survey

class SurveysFragment : Fragment() {
    private var _binding: FragmentSurveysBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SurveysAdapter
    private val viewModel: RootSurveyViewModel by viewModels()
    private val survey = Survey()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSurveysBinding.inflate(inflater, container, false)
        recyclerView = binding.rvListOfSurveys
        adapter = SurveysAdapter(requireContext().applicationContext as Application)
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mapOfSurvey.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }


        binding.floatingActionButton.setOnClickListener {
            val action = SurveysFragmentDirections.actionSurveysFragment2ToAddSurveyFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}