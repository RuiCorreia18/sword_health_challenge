package com.example.swordhealthchallenge.domain.Model

data class FavouriteCat(
    val id: String = "",
    val breed: String = "",
    val imageUrl: String = "",
    val imageId: String = "",
    val lifeSpan: String = "",
    var favourite: Boolean = true
)
