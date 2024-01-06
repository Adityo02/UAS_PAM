package com.example.outdoor.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface PenyewaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(penyewa: Penyewa)

    @Update
    suspend fun update(penyewa: Penyewa)

    @Delete
    suspend fun  delete(penyewa: Penyewa)

    @Query("SELECT * from penyewa WHERE id = :id")
    fun getPenyewa(id: Int): Flow<Penyewa>

    @Query("SELECT * from penyewa ORDER BY nama ASC")
    fun getAllPenyewa():Flow<List<Penyewa>>
}