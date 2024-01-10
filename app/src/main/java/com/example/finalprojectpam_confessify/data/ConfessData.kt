package com.example.finalprojectpam_confessify.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblConfess")
data class ConfessData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val fess: String,
    val username: String,
    val email: String
)
