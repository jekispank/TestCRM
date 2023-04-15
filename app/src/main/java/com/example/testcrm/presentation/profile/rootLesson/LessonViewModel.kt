package com.example.testcrm.presentation.profile.rootLesson

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testcrm.data.CrmRepositoryImpl
import com.example.testcrm.domain.*
import com.example.testcrm.presentation.models.Lesson
import com.example.testcrm.presentation.models.Student
import kotlinx.coroutines.launch

class LessonViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CrmRepositoryImpl(application)
    private val getListOfStudentUseCase = GetListOfStudentUseCase(repository)
    private val addLessonUseCase = AddLessonUseCase(repository)
    private val getListOfLessonUseCase = GetListOfLessonUseCase(repository)
    private val getLessonByIdUseCase = GetLessonByIdUseCase(repository)
    private val updateLessonUseCase = UpdateLessonUseCase(repository)

    private var _listOfStudent = MutableLiveData<List<Student>>()
    val listOfStudent: LiveData<List<Student>> = _listOfStudent

    private var _listOfLesson = MutableLiveData<List<Lesson>>()
    val listOfLesson: LiveData<List<Lesson>> = _listOfLesson

    private var _lesson = MutableLiveData<Lesson>()
    val lesson: LiveData<Lesson> = _lesson

    fun getListOfStudent() {
        viewModelScope.launch {
            val result = getListOfStudentUseCase.getListOfStudent()
            _listOfStudent.value = result
        }
    }

    fun addLesson(lesson: Lesson) {
        viewModelScope.launch {
            addLessonUseCase.addLesson(lesson)
        }
    }

    fun getListOfLesson(date: String) {
        viewModelScope.launch {
            val result = getListOfLessonUseCase.getListOfLesson(date)
            _listOfLesson.value = result
        }
    }

    fun getLesson(lessonId: Int) {
        viewModelScope.launch {
            val result = getLessonByIdUseCase.getLessonById(lessonId)
            _lesson.value = result
        }
    }

    fun updateLesson(lesson: Lesson) {
        viewModelScope.launch {
            updateLessonUseCase.updateLesson(lesson)
        }
    }
}