package com.example.testcrm.data

import androidx.room.*
import com.example.testcrm.data.models.LessonDbModel
import com.example.testcrm.data.models.StudentDbModel
import com.example.testcrm.presentation.models.Lesson

@Dao
interface CrmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent (studentDbModel: StudentDbModel)

    @Query("SELECT * FROM studentdbmodel")
    suspend fun getListOfStudent(): List<StudentDbModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLesson (lessonDbModel: LessonDbModel)

    @Query("SELECT * FROM lessondbmodel WHERE date = :date")
    suspend fun getListOfLesson(date: String): List<LessonDbModel>

    @Query("SELECT * FROM lessondbmodel WHERE id = :lessonId")
    suspend fun getLessonById (lessonId: Int): LessonDbModel

    @Update
    suspend fun updateLesson(lessonDbModel: LessonDbModel)
}