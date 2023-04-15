package com.example.testcrm.presentation.models

data class Lesson(
    val id: Int,
    val lessonDate: String,
    val title: String,
    val level: String,
    val grade: Int,
    val description: String,
    val student: String
)