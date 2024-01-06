package com.example.outdoor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penyewa")
data class Penyewa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String = "",
    val nohp: String = "",
    val alamat: String = "",
    val sewa: String=""
)
