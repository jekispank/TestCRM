package com.example.testcrm.domain

import com.example.testcrm.presentation.models.Student

class GetListOfStudentUseCase(private val crmRepository: CrmRepository) {

    suspend fun getListOfStudent(): List<Student> {
        return crmRepository.getListOfStudent()
    }
}