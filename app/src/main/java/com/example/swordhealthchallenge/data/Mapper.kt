package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatByImageResponse
import com.example.swordhealthchallenge.data.entities.CatDetailsResponse
import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.data.entities.FavouriteCatResponse
import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.Model.CatDetails
import com.example.swordhealthchallenge.domain.Model.FavouriteCat
import com.example.swordhealthchallenge.domain.Model.FavouriteInfo

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

fun CatDetailsResponse.toDomainModel(): CatDetails {
    return CatDetails(
        id = this.id,
        breed = this.breed,
        imageId = this.imageId,
        origin = this.origin,
        temperament = this.temperament,
        description = this.description
    )
}

fun List<FavouriteCatResponse>.toDomainModelList(): List<FavouriteInfo> {
    return this.map {
        FavouriteInfo(
            favouriteId = it.id,
            imageId = it.image_id,
        )
    }
}
