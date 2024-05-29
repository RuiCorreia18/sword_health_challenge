package com.example.swordhealthchallenge.domain.model

data class FavouriteCat(
    val id: String = "",
    var favouriteId: String = "",
    val breed: String = "",
    val imageUrl: String = "",
    val imageId: String = "",
    val lifeSpan: String = "",
)

data class FavouriteInfo(
    val favouriteId: String = "",
    val imageId: String = "",
)
