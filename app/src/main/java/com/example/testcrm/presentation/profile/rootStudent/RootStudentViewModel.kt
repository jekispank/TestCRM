package com.example.testcrm.presentation.profile.rootStudent

import android.app.Application
import androidx.lifecycle.*
import com.example.testcrm.data.CrmRepositoryImpl
import com.example.testcrm.domain.AddStudentUseCase
import com.example.testcrm.domain.CrmRepository
import com.example.testcrm.domain.GetListOfStudentUseCase
import com.example.testcrm.presentation.models.Student
import kotlinx.coroutines.launch

class RootStudentViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CrmRepositoryImpl(application)
    private val addStudentUseCase = AddStudentUseCase(repository)
    private val getListOfStudentUseCase = GetListOfStudentUseCase(repository)

    private val _listOfStudent = MutableLiveData<List<Student>>()
    val listOfStudent: LiveData<List<Student>> = _listOfStudent

    fun addStudent(student: Student) {
        viewModelScope.launch {
            addStudentUseCase.addStudent(student)
        }
    }

    fun getListOfStudent() {
        viewModelScope.launch {
            val result = getListOfStudentUseCase.getListOfStudent()
            _listOfStudent.value = result
        }
    }
}