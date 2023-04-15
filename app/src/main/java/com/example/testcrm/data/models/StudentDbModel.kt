package com.example.testcrm.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo
    var fullName: String,
    @ColumnInfo
    var telegram: String,
    @ColumnInfo
    var phoneNumber: String
)
