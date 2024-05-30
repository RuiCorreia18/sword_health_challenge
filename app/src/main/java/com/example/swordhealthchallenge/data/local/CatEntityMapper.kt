package com.example.swordhealthchallenge.data.local

import com.example.swordhealthchallenge.data.local.entity.CatEntity
import com.example.swordhealthchallenge.data.remote.model.CatResponse

fun CatResponse.toEntity(): CatEntity {
    return CatEntity(
        id = this.id,
        breed = this.name,
        imageUrl = this.image.url,
        imageId = this.image.id,
        favouriteId = "",
        origin = this.origin,
        temperament = this.temperament,
        description = this.description,
    )
}