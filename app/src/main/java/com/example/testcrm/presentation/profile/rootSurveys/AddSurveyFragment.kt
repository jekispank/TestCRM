package com.example.testcrm.presentation.profile.rootSurveys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testcrm.R
import com.example.testcrm.databinding.FragmentAddSurveyBinding

class AddSurveyFragment : Fragment() {
    private var _binding: FragmentAddSurveyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RootSurveyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSurveyBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAddSurvey.setOnClickListener {
            viewModel.addSurvey(
                binding.surveyName.text.toString(),
                binding.surveyLink.text.toString()
            )
            val action =
                AddSurveyFragmentDirections.actionAddSurveyFragmentToSurveysFragment2()
            findNavController().navigate(action)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}