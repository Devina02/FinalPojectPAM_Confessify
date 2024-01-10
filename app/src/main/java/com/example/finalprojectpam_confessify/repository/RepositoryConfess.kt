package com.example.finalprojectpam_confessify.repository

import com.example.finalprojectpam_confessify.data.ConfessData
import kotlinx.coroutines.flow.Flow

interface RepositoryConfess {
    fun getAllConfessStream():Flow<List<ConfessData>>

    fun getConfessStream(idn: Int): Flow<ConfessData?>

    suspend fun insertConfess(confessData: ConfessData)

    suspend fun deleteConfess(confessData: ConfessData)

    suspend fun updateConfess(confessData: ConfessData)
}