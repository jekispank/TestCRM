package com.example.testcrm.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testcrm.R
import com.example.testcrm.databinding.FragmentRootProfileBinding
import com.example.testcrm.presentation.profile.ProfileFragment
import com.example.testcrm.presentation.profile.rootLesson.RootScheduleFragment
import com.example.testcrm.presentation.profile.rootStudent.RootStudentFragment
import com.example.testcrm.presentation.profile.rootSurveys.RootSurveysFragment

class RootProfileFragment : Fragment() {
    private var _binding: FragmentRootProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRootProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.root_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bt_profile -> {
                    navigateTo(ProfileFragment())
                }
                R.id.bt_schedule
                -> {
                    navigateTo(RootScheduleFragment())
                }
                R.id.bt_students
                -> {
                    navigateTo(RootStudentFragment())
                }
                R.id.bt_surveys
                -> {
                    navigateTo(RootSurveysFragment())
                }
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateTo(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.root_container, fragment)
            .commit()
    }
}