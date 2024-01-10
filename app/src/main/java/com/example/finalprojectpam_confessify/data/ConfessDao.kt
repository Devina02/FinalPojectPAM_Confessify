package com.example.finalprojectpam_confessify.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface ConfessDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(confess: ConfessData)

    @Update
    suspend fun update(confess: ConfessData)

    @Delete
    suspend fun delete(confess: ConfessData)

    @Query("SELECT * from tblConfess WHERE id = :id")
    fun getConfess(id: Int): Flow<ConfessData>

    @Query("SELECT * from tblConfess ORDER BY fess ASC")
    fun getAllConfess(): Flow<List<ConfessData>>
}