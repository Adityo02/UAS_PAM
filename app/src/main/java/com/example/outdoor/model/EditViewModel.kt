package com.example.outdoor.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.outdoor.repositori.PenyewaRepository
import com.example.outdoor.ui.halaman.RouteEdit
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val penyewaRepository: PenyewaRepository
) : ViewModel(){
    var sewaUiState by mutableStateOf(UIStatePenyewa())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[RouteEdit.itemIdArg])

    init {
        viewModelScope.launch {
            sewaUiState = penyewaRepository.getPenyewaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStatePenyewa(true)
        }
    }

    suspend fun updatePenyewa() {
        if (validasiInput(sewaUiState.penyewaBio)) {
            penyewaRepository.updatePenyewa(sewaUiState.penyewaBio.toPenyewa())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(penyewaBio: PenyewaBio) {
        sewaUiState =
            UIStatePenyewa(penyewaBio = penyewaBio, isEntryValid = validasiInput(penyewaBio))
    }

    private fun validasiInput(uiState: PenyewaBio = sewaUiState.penyewaBio ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && sewa.isNotBlank()
        }
    }
}