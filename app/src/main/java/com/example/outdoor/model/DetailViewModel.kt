package com.example.outdoor.model

import androidx.lifecycle.SavedStateHandle
import com.example.outdoor.repositori.PenyewaRepository

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val penyewaRepository: PenyewaRepository
){}