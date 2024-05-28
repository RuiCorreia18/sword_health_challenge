package com.example.swordhealthchallenge.domain.Model

data class FavouriteCat(
    val id: String = "",
    val favouriteId: String = "",
    val breed: String = "",
    val imageUrl: String = "",
    val imageId: String = "",
    val lifeSpan: String = "",
)

data class FavouriteInfo(
    val favouriteId: String = "",
    val imageId: String = "",
)
