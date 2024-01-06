package com.example.outdoor.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface PenyewaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(penyewa: Penyewa)

    @Update
    suspend fun update(penyewa: Penyewa)

    @Delete
    suspend fun  delete(penyewa: Penyewa)
}