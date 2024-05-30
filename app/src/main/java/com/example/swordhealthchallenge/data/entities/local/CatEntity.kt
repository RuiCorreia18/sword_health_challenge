package com.example.swordhealthchallenge.data.entities.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val id: String,
    val breed: String,
    val imageUrl: String,
    val imageId: String,
    var favouriteId: String = ""
)
