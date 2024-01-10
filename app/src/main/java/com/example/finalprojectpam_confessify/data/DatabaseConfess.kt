package com.example.finalprojectpam_confessify.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConfessData::class], version = 1, exportSchema = false)
abstract class DatabaseConfess : RoomDatabase() {

    abstract fun confessDao() : ConfessDao

    companion object {
        @Volatile
        private var Instance: DatabaseConfess? = null

        fun getDatabase(context: Context): DatabaseConfess {
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context, DatabaseConfess::class.java, "confess_database")
                    .build().also { Instance=it }
            })
        }
    }
}