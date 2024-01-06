package com.example.outdoor.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface PenyewaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(penyewa: Penyewa)
}