package com.example.swordhealthchallenge.ui.utils

import com.example.swordhealthchallenge.domain.model.CatDetails

object CatDetailsDomainModelFakes {
    val catDetailsNoFav1 = CatDetails(
        id = "1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = "",
        origin = "Origin1",
        temperament = "Temperament1",
        description = "Description1",
    )
    val catDetailsFav1 = CatDetails(
        id = "1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = "FavId1",
        origin = "Origin1",
        temperament = "Temperament1",
        description = "Description1",
    )
}
