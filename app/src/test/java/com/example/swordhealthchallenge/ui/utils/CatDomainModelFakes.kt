package com.example.swordhealthchallenge.ui.utils

import com.example.swordhealthchallenge.domain.model.Cat

object CatDomainModelFakes {

    val catNoFav = Cat(
        id = "CatId1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = ""
    )
    val catFav = Cat(
        id = "CatId1",
        breed = "Breed1",
        imageUrl = "URL1",
        imageId = "ImageId1",
        favouriteId = "FavId1"
    )
}
