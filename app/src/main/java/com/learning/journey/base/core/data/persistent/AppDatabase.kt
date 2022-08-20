package com.learning.journey.base.core.data.persistent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learning.journey.base.core.data.persistent.entities.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "learningdemo.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }

}