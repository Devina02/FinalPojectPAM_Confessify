package com.example.finalprojectpam_confessify.repository

import com.example.finalprojectpam_confessify.data.ConfessDao
import com.example.finalprojectpam_confessify.data.ConfessData
import kotlinx.coroutines.flow.Flow

class OfflineRepo(private val confessDao: ConfessDao):RepositoryConfess {
    override fun getAllConfessStream(): Flow<List<ConfessData>> {
        return confessDao.getAllConfess()
    }

    override fun getConfessStream(id: Int): Flow<ConfessData?> {
        return confessDao.getConfess(id)
    }

    override suspend fun insertConfess(confessData: ConfessData) {
        return confessDao.insert(confessData)
    }

    override suspend fun deleteConfess(confessData: ConfessData) {
        return confessDao.delete(confessData)
    }

    override suspend fun updateConfess(confessData: ConfessData) {
        return confessDao.update(confessData)
    }
}