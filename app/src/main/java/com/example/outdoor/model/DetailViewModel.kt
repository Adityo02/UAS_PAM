package com.example.outdoor.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.outdoor.repositori.PenyewaRepository
import com.example.outdoor.ui.halaman.RouteDetail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val penyewaRepository: PenyewaRepository
) : ViewModel() {
    private val penyewaId: Int = checkNotNull(savedStateHandle[RouteDetail.sewaIdArg])
    val uiState: StateFlow<ItemDetailUiState> =
        penyewaRepository.getPenyewaStream(penyewaId).filterNotNull()
            .map { ItemDetailUiState(penyewaBio = it.toDetailPenyewa()) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailUiState()
            )

    suspend fun deleteItem() {
        penyewaRepository.deletePenyewa(uiState.value.penyewaBio.toPenyewa())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailUiState(
    val outOfStock: Boolean = true,
    val penyewaBio: PenyewaBio = PenyewaBio()
)