package com.example.testcrm.domain

import com.example.testcrm.presentation.models.Lesson

class AddLessonUseCase(private val crmRepository: CrmRepository) {

    suspend fun addLesson(lesson: Lesson) {
        crmRepository.addLesson(lesson)
    }
}