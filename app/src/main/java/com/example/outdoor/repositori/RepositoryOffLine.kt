package com.example.outdoor.repositori

import com.example.outdoor.data.Penyewa
import com.example.outdoor.data.PenyewaDao
import kotlinx.coroutines.flow.Flow

class RepositoryOffLine(private val penyewaDao: PenyewaDao) : PenyewaRepository {
    override fun getAllPenyewaStream(): Flow<List<Penyewa>> {
        return penyewaDao.getAllPenyewa()
    }

    override fun getPenyewaStream(id: Int): Flow<Penyewa?> {
        return penyewaDao.getPenyewa(id)
    }

    override suspend fun insertPenyewa(penyewa: Penyewa) {
        return penyewaDao.insert(penyewa)
    }

    override suspend fun updatePenyewa(penyewa: Penyewa) {
        return penyewaDao.update(penyewa)
    }

    override suspend fun deletePenyewa(penyewa: Penyewa) {
        return penyewaDao.delete(penyewa)
    }
}