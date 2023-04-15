package com.example.testcrm.domain

import com.example.testcrm.presentation.models.Lesson
import com.example.testcrm.presentation.models.Student

interface CrmRepository {
    suspend fun addStudent(student: Student)

    suspend fun getListOfStudent(): List<Student>

    suspend fun addLesson(lesson: Lesson)

    suspend fun getListOfLesson(date: String): List<Lesson>

    suspend fun getLessonById(lessonId: Int): Lesson

    suspend fun updateLesson(lesson: Lesson)
}