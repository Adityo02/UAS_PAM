package com.example.outdoor.data

import androidx.room.RoomDatabase

abstract class DatabaseOutdoor : RoomDatabase(){
    abstract fun penyewaDao(): PenyewaDao
}