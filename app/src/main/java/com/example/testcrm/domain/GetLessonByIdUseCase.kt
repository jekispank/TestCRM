package com.example.testcrm.domain

import com.example.testcrm.presentation.models.Lesson

class GetLessonByIdUseCase(private val crmRepository: CrmRepository) {

    suspend fun getLessonById(lessonId: Int): Lesson {
        return crmRepository.getLessonById(lessonId)
    }
}