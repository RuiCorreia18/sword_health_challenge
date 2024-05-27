package com.example.swordhealthchallenge.domain.Model

data class CatDetails(
    val id: String,
    val breed: String,
    var imageUrl: String = "",
    val imageId: String,
    var favourite: Boolean = false,
    val origin: String,
    val temperament: String,
    val description: String,
)