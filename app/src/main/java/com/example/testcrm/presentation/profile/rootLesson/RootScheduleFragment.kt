package com.example.testcrm.presentation.profile.rootLesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testcrm.R
import com.example.testcrm.databinding.FragmentRootScheduleBinding

class RootScheduleFragment : Fragment() {
    private var _binding: FragmentRootScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRootScheduleBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.schedule_root_container) as NavHostFragment

        navController = navHostFragment.navController
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}