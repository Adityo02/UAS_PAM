package com.example.outdoor.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.outdoor.AdvantureApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(adventureApp().dataContainer.penyewaRepository) }
        initializer {
            EntryViewModel(adventureApp().dataContainer.penyewaRepository)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                adventureApp().dataContainer.penyewaRepository
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                adventureApp().dataContainer.penyewaRepository
            )
        }
    }
}

fun CreationExtras.adventureApp(): AdvantureApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AdvantureApp)