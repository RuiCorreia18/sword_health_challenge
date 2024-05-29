package com.example.swordhealthchallenge.domain.model

data class CatDetails(
    val id: String,
    val breed: String,
    var imageUrl: String = "",
    val imageId: String,
    var favouriteId: String = "",
    val origin: String,
    val temperament: String,
    val description: String,
)
