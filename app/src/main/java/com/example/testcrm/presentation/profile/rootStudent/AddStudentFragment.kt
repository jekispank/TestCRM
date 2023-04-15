package com.example.testcrm.presentation.profile.rootStudent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testcrm.databinding.FragmentAddStudentBinding
import com.example.testcrm.presentation.models.Student

class AddStudentFragment : Fragment() {
    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RootStudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAddStudent.setOnClickListener {
            if (validationUserData()) {
                viewModel.addStudent(getUserData())
                val action =
                    AddStudentFragmentDirections.actionAddStudentFragmentToStudentsFragment3()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Enter the correct data!", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUserData(): Student {
        val fullName =
            "${
                binding.enterFirstName.text.toString().lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            } ${
                binding.enterLastName.text.toString().lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            }"
        val telegram = binding.enterTelegram.text.trim().toString()
        val phoneNumber = binding.enterPhoneNumber.text.trim().toString()

        return Student(
            0,
            fullName,
            telegram,
            phoneNumber
        )
    }

    private fun validationUserData(): Boolean {
        return binding.enterFirstName.text.isNotEmpty()
                && binding.enterLastName.text.isNotEmpty()
                && binding.enterTelegram.text.isNotEmpty()
                && binding.enterPhoneNumber.text.isNotEmpty()
    }
}