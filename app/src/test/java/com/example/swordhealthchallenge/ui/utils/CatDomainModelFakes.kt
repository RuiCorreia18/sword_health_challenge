package com.example.swordhealthchallenge.ui.utils

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
}
