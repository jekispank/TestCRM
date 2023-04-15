package com.example.testcrm.presentation.profile.rootStudent

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testcrm.databinding.FragmentStudentsBinding

class StudentsFragment : Fragment() {

    private var _binding: FragmentStudentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val viewModel: RootStudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStudentsBinding.inflate(inflater, container, false)
        viewModel.getListOfStudent()
        recyclerView = binding.rvListOfStudents
        adapter = StudentAdapter(requireContext().applicationContext as Application)
        recyclerView.adapter = adapter
        viewModel.listOfStudent.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val action = StudentsFragmentDirections.actionStudentsFragment3ToAddStudentFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}