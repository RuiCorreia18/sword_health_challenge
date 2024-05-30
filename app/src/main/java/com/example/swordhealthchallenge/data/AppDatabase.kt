package com.example.swordhealthchallenge.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.swordhealthchallenge.data.entities.local.CatEntity

@Database(entities = [CatEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}