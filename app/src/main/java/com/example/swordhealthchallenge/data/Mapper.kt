package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatByImageResponse
import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.Model.FavouriteCat

fun List<CatResponse>.toDomainList(): List<Cat> {
    return this.map {
        Cat(
            id = it.id,
            breed = it.name,
            imageUrl = it.image.url ?: "",
            imageId = it.image.id ?: "",
        )
    }
}

fun CatByImageResponse.toDomainModel(): FavouriteCat {
    return FavouriteCat(
        id = this.breeds.first().id,
        breed = this.breeds.first().name,
        imageUrl = this.url,
        imageId = this.imageId,
        lifeSpan = this.breeds.first().life_span.split(" ").first()
    )
}
