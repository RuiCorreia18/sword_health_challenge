package com.example.swordhealthchallenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val id: String,
    val breed: String,
    val imageUrl: String,
    val imageId: String,
    var favouriteId: String,
    val origin: String,
    val temperament: String,
    val description: String,
)
