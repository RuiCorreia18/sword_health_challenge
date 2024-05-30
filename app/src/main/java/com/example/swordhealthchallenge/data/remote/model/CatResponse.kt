package com.example.swordhealthchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: CatImageResponse,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("description")
    val description: String,
)

data class CatImageResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
)

data class CatDetailsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val breed: String,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("reference_image_id")
    val imageId: String,
)
