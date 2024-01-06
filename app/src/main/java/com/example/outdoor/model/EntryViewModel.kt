package com.example.outdoor.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.outdoor.data.Penyewa
import com.example.outdoor.repositori.PenyewaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EntryViewModel(private val penyewaRepository: PenyewaRepository) : ViewModel() {
    private val _stateUI = MutableStateFlow(Penyewa())
    val stateUI: StateFlow<Penyewa> = _stateUI.asStateFlow()
    var uiStatePenyewa by mutableStateOf(UIStatePenyewa())
        private set

    private fun nullValidation(uiState: PenyewaBio = uiStatePenyewa.penyewaBio): Boolean {
        return with(uiState) {
            nama.isNotBlank() && nohp.isNotBlank() && sewa.isNotBlank()
        }
    }

    fun setBarang(barangSewa: String) {
        _stateUI.update { stateSaatIni -> stateSaatIni.copy(sewa = barangSewa) }

    }

    fun updateUiState(penyewaBio: PenyewaBio) {
        uiStatePenyewa =
            UIStatePenyewa(penyewaBio = penyewaBio, isEntryValid = nullValidation(penyewaBio))
    }

    suspend fun savePenyewa() {
        if (nullValidation()) {
            penyewaRepository.insertPenyewa(uiStatePenyewa.penyewaBio.toPenyewa())
        }
    }

}

data class UIStatePenyewa(
    val penyewaBio: PenyewaBio = PenyewaBio(),
    val isEntryValid: Boolean = false
)

data class PenyewaBio(
    val id: Int = 0,
    val nama: String = "",
    val nohp: String = "",
    val alamat: String = "",
    val sewa: String = ""
)

fun PenyewaBio.toPenyewa(): Penyewa = Penyewa(
    id = id,
    nama = nama,
    nohp = nohp,
    alamat = alamat,
    sewa = sewa
)

fun Penyewa.toUiStatePenyewa(isEntryValid: Boolean = false): UIStatePenyewa = UIStatePenyewa(
    penyewaBio = this.toDetailPenyewa(),
    isEntryValid = isEntryValid
)

fun Penyewa.toDetailPenyewa(): PenyewaBio = PenyewaBio(
    id = id,
    nama = nama,
    nohp = nohp,
    alamat = alamat,
    sewa = sewa
)
