package com.example.testcrm.domain

import com.example.testcrm.presentation.models.Lesson

class GetListOfLessonUseCase(private val crmRepository: CrmRepository) {

    suspend fun getListOfLesson(date: String): List<Lesson> {
       return crmRepository.getListOfLesson(date)
    }
}