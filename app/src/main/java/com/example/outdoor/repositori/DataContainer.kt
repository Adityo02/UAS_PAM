package com.example.outdoor.repositori

import android.content.Context
import com.example.outdoor.data.DatabaseOutdoor

interface ContainerApp {
    val penyewaRepository: PenyewaRepository
}

class DataContainer(private val context: Context) : ContainerApp {
    override val penyewaRepository: PenyewaRepository by lazy {
        RepositoryOffLine(DatabaseOutdoor.getDatabase(context).penyewaDao())
    }
}