package com.example.testcrm.presentation.profile.rootLesson

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testcrm.databinding.FragmentAddLessonBinding
import com.example.testcrm.presentation.Grade
import com.example.testcrm.presentation.Level
import com.example.testcrm.presentation.models.Lesson
private const val MISSING_ID = 0
class AddLessonFragment : Fragment() {
    private val args: AddLessonFragmentArgs by navArgs()
    private var _binding: FragmentAddLessonBinding? = null
    private val binding get() = _binding!!
    private var studentsList: List<String> = emptyList()
    private val viewModel: LessonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddLessonBinding.inflate(inflater, container, false)
        Log.d("AddLessonFragment", "Lesson id is ${args.lessonId}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListOfStudent()
        viewModel.listOfStudent.observe(viewLifecycleOwner) { list ->
            studentsList = list.map { it.fullName }
            setSpinnerList()
        }
        if (args.lessonId == MISSING_ID) {

            val date = args.currentDate
            binding.enterDate.text = date
            binding.btAddLesson.setOnClickListener {
                viewModel.addLesson(getLesson(args.lessonId))
                val action =
                    AddLessonFragmentDirections.actionAddLessonFragmentToScheduleFragment2()
                findNavController().navigate(action)
            }
        }
        else {
            viewModel.getLesson(args.lessonId)
            viewModel.lesson.observe(viewLifecycleOwner) {
                binding.apply {
                    tvSelectedStudent.text = it.student
                    enterSubject.setText(it.title)
                    enterDate.text = it.lessonDate
                    tvSelectedLevel.text = it.level
                    tvSelectedGrade.text = it.grade.toString()
                    description.setText(it.description)
                }
            }
            binding.btAddLesson.setOnClickListener {
                viewModel.updateLesson(getLesson(args.lessonId))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getLesson(id: Int): Lesson {
        return Lesson(
            id,
            binding.enterDate.text.toString(),
            binding.enterSubject.text.toString(),
            binding.tvSelectedLevel.text.toString(),
            binding.tvSelectedGrade.text.toString().toInt(),
            binding.description.text.toString(),
            binding.tvSelectedStudent.text.toString()
        )
    }

    private fun setSpinnerList() {
        val spinAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            studentsList
        )
        binding.chooseStudentSpinner.adapter = spinAdapter
        binding.chooseStudentSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    binding.tvSelectedStudent.text = studentsList[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        val spinLevelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            Level().getListOfLevel()
        )
        binding.chooseLevelSpinner.adapter = spinLevelAdapter
        binding.chooseLevelSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    binding.tvSelectedLevel.text = Level().getListOfLevel()[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        val spinGradeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            Grade().getListOfGrade()
        )
        binding.chooseGradeSpinner.adapter = spinGradeAdapter
        binding.chooseGradeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    binding.tvSelectedGrade.text = Grade().getListOfGrade()[p2].toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }
}