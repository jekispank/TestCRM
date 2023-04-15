package com.example.testcrm.presentation.profile.rootLesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testcrm.databinding.FragmentScheduleBinding
import java.text.SimpleDateFormat

class ScheduleFragment : Fragment() {

    private var currentDate: String = ""
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LessonViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        recyclerView = binding.rvListOfLesson
        adapter = ScheduleAdapter()
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton2.setOnClickListener {
            val action =
                ScheduleFragmentDirections.actionScheduleFragmentToLessonStatisticFragment()
            findNavController().navigate(action)
        }

        val date = binding.calendar.date
        currentDate = SimpleDateFormat("d.M.yyyy").format(date)
        updateListOfLesson()

        binding.calendar.setOnDateChangeListener { calendarView, i, i2, i3 ->
            currentDate = "$i3.${i2 + 1}.$i"
            updateListOfLesson()
        }

        binding.floatingActionButton.setOnClickListener {
            val action =
                ScheduleFragmentDirections.actionScheduleFragmentToAddLessonFragment(
                    currentDate,
                    MISSING_ID
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateListOfLesson() {
        viewModel.getListOfLesson(currentDate)
        viewModel.listOfLesson.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
    }

    companion object {
        private const val MISSING_ID = 0
    }
}