package com.example.testcrm.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LessonDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo
    var date: String,
    @ColumnInfo
    var title: String,
    @ColumnInfo
    var level: String,
    @ColumnInfo
    var grade: Int,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var student: String
)
