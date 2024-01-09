package com.example.finalprojectpam_confessify.repository

import com.example.finalprojectpam_confessify.data.ConfessData
import com.example.finalprojectpam_confessify.service_api.ConfessService

interface ConfessRepository {
    suspend fun getConfess(): List<ConfessData>

    suspend fun insertConfess(confess: ConfessData)

    suspend fun updateConfess(confess: ConfessData)
}

class NetworkConfessRepository(
    private val ConfessApiService: ConfessService
) : ConfessRepository {
    override suspend fun getConfess(): List<ConfessData> = ConfessApiService.getConfess()

    override suspend fun insertConfess(confess: ConfessData) {
        ConfessApiService.insertConfess(confess)
    }

    override suspend fun updateConfess(confess: ConfessData) {
        ConfessApiService.updateConfess(confess)
    }
}