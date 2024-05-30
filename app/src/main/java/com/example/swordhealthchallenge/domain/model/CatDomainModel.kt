package com.example.swordhealthchallenge.domain.model

import com.example.swordhealthchallenge.data.entities.local.CatEntity

data class CatDomainModel(
    val id: String,
    val breed: String,
    val imageUrl: String,
    val imageId: String,
    var favouriteId: String = ""
) {
    fun toEntity(): CatEntity {
        return CatEntity(
            id, breed, imageUrl, imageId, favouriteId
        )
    }
}
