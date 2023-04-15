package com.example.testcrm.data

import com.example.testcrm.data.models.LessonDbModel
import com.example.testcrm.data.models.StudentDbModel
import com.example.testcrm.presentation.models.Lesson
import com.example.testcrm.presentation.models.Student

class ModelsMapper {

    fun studentEntityToDbModel(student: Student): StudentDbModel {

        return StudentDbModel(
            id = student.id,
            fullName = student.fullName,
            telegram = student.telegram,
            phoneNumber = student.phoneNumber
        )
    }

    fun studentDbEntityToModel(studentDbModel: StudentDbModel): Student {
        return Student(
            id = studentDbModel.id,
            fullName = studentDbModel.fullName,
            telegram = studentDbModel.telegram,
            phoneNumber = studentDbModel.phoneNumber
        )
    }

    fun listOfDbEntityToListOfModels(list: List<StudentDbModel>): List<Student> {
        return list.map { studentDbEntityToModel(it) }
    }

    fun lessonEntityToDbModel(lesson: Lesson): LessonDbModel {
        return LessonDbModel(
            id = lesson.id,
            title = lesson.title,
            grade =lesson.grade,
            date = lesson.lessonDate,
            level = lesson.level,
            description = lesson.description,
            student = lesson.student
        )
    }

    fun lessonDbEntityToModel(lessonDbModel: LessonDbModel): Lesson {
        return Lesson(
            id = lessonDbModel.id,
            title = lessonDbModel.title,
            grade =lessonDbModel.grade,
            lessonDate = lessonDbModel.date,
            level = lessonDbModel.level,
            description = lessonDbModel.description,
            student = lessonDbModel.student
        )
    }

    fun listOfDbEntityToListOfLesson(list: List<LessonDbModel>): List<Lesson> {
        return list.map { lessonDbEntityToModel(it) }
    }
}