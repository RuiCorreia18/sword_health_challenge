package com.example.swordhealthchallenge.utils

import com.example.swordhealthchallenge.domain.model.CatDetailsDomainModel

object CatDetailsDomainModelFakes {
    val catDetailsDomainModelNoFav1 = CatDetailsDomainModel(
        id = "1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = "",
        origin = "Origin1",
        temperament = "Temperament1",
        description = "Description1",
    )
    val catDetailsDomainModelFav1 = CatDetailsDomainModel(
        id = "1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = "FavId1",
        origin = "Origin1",
        temperament = "Temperament1",
        description = "Description1",
    )

    val catDetailsDomainModelMock = CatDetailsDomainModel(
        id = "1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = "FavId1",
        origin = "Origin",
        temperament = "Temperament",
        description = "Descrition",
    )
}
