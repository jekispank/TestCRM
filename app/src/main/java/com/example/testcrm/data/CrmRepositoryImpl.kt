package com.example.testcrm.data

import android.app.Application
import com.example.testcrm.data.models.StudentDbModel
import com.example.testcrm.domain.CrmRepository
import com.example.testcrm.presentation.models.Lesson
import com.example.testcrm.presentation.models.Student

class CrmRepositoryImpl(application: Application) : CrmRepository {

    private val crmDao = CrmDatabase.getDatabase(application).getDao()
    private val mapper = ModelsMapper()

    override suspend fun addStudent(student: Student) {
        crmDao.insertStudent(mapper.studentEntityToDbModel(student))
    }

    override suspend fun getListOfStudent(): List<Student> {
        return mapper.listOfDbEntityToListOfModels(crmDao.getListOfStudent())
    }

    override suspend fun addLesson(lesson: Lesson) {
        crmDao.insertLesson(mapper.lessonEntityToDbModel(lesson))
    }

    override suspend fun getListOfLesson(date: String): List<Lesson> {
        return mapper.listOfDbEntityToListOfLesson(crmDao.getListOfLesson(date))
    }

    override suspend fun getLessonById(lessonId: Int): Lesson {
        return mapper.lessonDbEntityToModel(crmDao.getLessonById(lessonId))
    }

    override suspend fun updateLesson(lesson: Lesson) {
        crmDao.updateLesson(mapper.lessonEntityToDbModel(lesson))
    }
}