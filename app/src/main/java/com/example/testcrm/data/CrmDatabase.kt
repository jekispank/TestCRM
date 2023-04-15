package com.example.testcrm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testcrm.data.models.LessonDbModel
import com.example.testcrm.data.models.StudentDbModel
import com.example.testcrm.data.models.TeacherDbModel

@Database(
    entities = [TeacherDbModel::class, StudentDbModel::class, LessonDbModel::class],
    version = 2,
    exportSchema = false
)
abstract class CrmDatabase : RoomDatabase() {
    abstract fun getDao(): CrmDao

    companion object {
        @Volatile
        private var INSTANCE: CrmDatabase? = null

        fun getDatabase(context: Context): CrmDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CrmDatabase::class.java,
                    "crm_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}