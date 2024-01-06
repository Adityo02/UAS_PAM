package com.example.outdoor.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

abstract class DatabaseOutdoor : RoomDatabase(){
    abstract fun penyewaDao(): PenyewaDao
    companion object {
        @Volatile
        private var Instance: DatabaseOutdoor? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): DatabaseOutdoor {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DatabaseOutdoor::class.java, "adventure_mountain")
                    .build()
                    .also { Instance = it }
            })
        }
    }
}