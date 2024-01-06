package com.example.outdoor.repositori

import com.example.outdoor.data.Penyewa
import kotlinx.coroutines.flow.Flow

interface PenyewaRepository {
    fun getAllPenyewaStream(): Flow<List<Penyewa>>
    fun getPenyewaStream(id: Int): Flow<Penyewa?>
    suspend fun insertPenyewa(penyewa: Penyewa)
    suspend fun updatePenyewa(penyewa: Penyewa)
    suspend fun deletePenyewa(penyewa: Penyewa)
}