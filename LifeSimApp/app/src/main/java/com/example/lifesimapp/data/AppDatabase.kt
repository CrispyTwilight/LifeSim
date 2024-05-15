// Auth: John O'Neal
// Date: 05/09/2024
// Desc: Define the database and set it up with the questions and options

package com.example.lifesimapp.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [QuestionEntity::class, OptionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            Log.d("AppDatabase", "getDatabase called")
            return INSTANCE ?: synchronized(this) {
                Log.d("AppDatabase", "Creating new database instance")
                // TODO: Delete the existing database file
                context.deleteDatabase("LifeSimAppDatabase")

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "LifeSimAppDatabase"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            Log.d("AppDatabase", "Database created")
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                // Call the function to populate the database with JSON data
                                Log.d("AppDatabase", "Populating database")
                                populateDatabase(context)
                                Log.d("AppDatabase", "Database populated in AppDatabase")
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                Log.d(
                    "AppDatabase",
                    "Database instance created"
                )  // Log when the database instance is created
                CoroutineScope(Dispatchers.IO).launch {
                    val questionCount = instance.questionDao().getQuestionCount()
                    Log.d("AppDatabase", "Number of questions in the database: $questionCount")
                }
                instance
            }
        }
    }
}