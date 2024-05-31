package com.example.swordhealthchallenge.utils

import com.example.swordhealthchallenge.domain.model.CatDomainModel

object CatDomainModelFakes {

    val catDomainModelNoFav = CatDomainModel(
        id = "CatId1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = ""
    )
    val catDomainModelFav = CatDomainModel(
        id = "CatId1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = "FavId1"
    )
    val catDomainModelNoFav2 = CatDomainModel(
        id = "CatId2",
        breed = "Breed2",
        imageUrl = "URL2",
        imageId = "ImageId2",
        favouriteId = ""
    )
}
