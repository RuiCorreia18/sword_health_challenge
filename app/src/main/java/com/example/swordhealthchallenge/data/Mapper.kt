package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.local.entity.CatEntity
import com.example.swordhealthchallenge.data.remote.model.CatByImageResponse
import com.example.swordhealthchallenge.data.remote.model.CatDetailsResponse
import com.example.swordhealthchallenge.data.remote.model.CatResponse
import com.example.swordhealthchallenge.data.remote.model.FavouriteCatResponse
import com.example.swordhealthchallenge.domain.model.CatDetailsDomainModel
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel

fun List<CatResponse>.toDomainList(): List<CatDomainModel> {
    return this.map {
        CatDomainModel(
            id = it.id,
            breed = it.name,
            imageUrl = it.image.url,
            imageId = it.image.id,
        )
    }
}

fun CatByImageResponse.toDomainModel(): FavouriteCatDomainModel {
    return FavouriteCatDomainModel(
        id = this.breeds.first().id,
        breed = this.breeds.first().name,
        imageUrl = this.url,
        imageId = this.imageId,
        lifeSpan = this.breeds.first().life_span.split(" ").first()
    )
}

fun CatDetailsResponse.toDomainModel(): CatDetailsDomainModel {
    return CatDetailsDomainModel(
        id = this.id,
        breed = this.breed,
        imageId = this.imageId,
        origin = this.origin,
        temperament = this.temperament,
        description = this.description
    )
}

fun List<FavouriteCatResponse>.toDomainModelList(): List<FavouriteInfoDomainModel> {
    return this.map {
        FavouriteInfoDomainModel(
            favouriteId = it.id,
            imageId = it.image_id,
        )
    }
}

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
        lifeSpan = this.life_span.split(" ").first()
    )
}

fun List<CatEntity>.toCatDomainModelList(): List<CatDomainModel> {
    return this.map {
        CatDomainModel(
            id = it.id,
            breed = it.breed,
            imageUrl = it.imageUrl,
            imageId = it.imageId,
            favouriteId = it.favouriteId
        )
    }
}

fun List<CatEntity>.toFavouriteCatDomainModelList(): List<FavouriteCatDomainModel> {
    return this.map {
        FavouriteCatDomainModel(
            id = it.id,
            breed = it.breed,
            imageUrl = it.imageUrl,
            imageId = it.imageId,
            lifeSpan = it.lifeSpan,
            favouriteId = it.favouriteId
        )
    }
}
