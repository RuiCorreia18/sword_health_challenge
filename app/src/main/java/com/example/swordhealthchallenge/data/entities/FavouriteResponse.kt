package com.example.swordhealthchallenge.data.entities

import com.google.gson.annotations.SerializedName


data class FavouriteCatResponse(
    @SerializedName("image_id")
    val image_id: String
)

data class CatByImageResponse(
    @SerializedName("breeds")
    val breeds: List<Breed>,
    @SerializedName("id")
    val imageId: String,
    @SerializedName("url")
    val url: String,
)

data class Breed(
    @SerializedName("id")
    val id: String,
    @SerializedName("life_span")
    val life_span: String,
    @SerializedName("name")
    val name: String,
)