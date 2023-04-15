package com.example.testcrm.domain

import com.example.testcrm.presentation.models.Student

class AddStudentUseCase(private val crmRepository: CrmRepository) {

    suspend fun addStudent(student: Student) {
        crmRepository.addStudent(student)
    }
}