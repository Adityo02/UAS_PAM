package com.example.outdoor.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.outdoor.data.Penyewa
import com.example.outdoor.repositori.PenyewaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val penyewaRepository: PenyewaRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> =
        penyewaRepository.getAllPenyewaStream().filterNotNull().map {
            HomeUiState(listPenyewa = it.toList())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    data class HomeUiState(
        val listPenyewa: List<Penyewa> = listOf()
    )
}