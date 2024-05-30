package com.example.swordhealthchallenge.ui.utils

import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel

object FavouritesDomainModelFakes {

    val favCat1 = FavouriteCatDomainModel(
        id = "1",
        favouriteId = "FavId1",
        breed = "Breed1",
        imageUrl = "ImageUrl1",
        imageId = "ImageId1",
        lifeSpan = "lifeSpan1",
    )
    val favInfo1 = FavouriteInfoDomainModel(
        favouriteId = "FavId1",
        imageId = "ImageId1"
    )

    val favCat2 = FavouriteCatDomainModel(
        id = "2",
        favouriteId = "FavId2",
        breed = "Breed2",
        imageUrl = "ImageUrl2",
        imageId = "ImageId2",
        lifeSpan = "lifeSpan2",
    )
    val favInfo2 = FavouriteInfoDomainModel(
        favouriteId = "FavId2",
        imageId = "ImageId2"
    )
}
