package com.example.testcrm.domain

import com.example.testcrm.presentation.models.Lesson

class UpdateLessonUseCase(private val crmRepository: CrmRepository) {

    suspend fun updateLesson(lesson: Lesson) {
        crmRepository.updateLesson(lesson)
    }
}