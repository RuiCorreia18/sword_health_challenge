package com.example.swordhealthchallenge.domain.model

data class FavouriteCatDomainModel(
    val id: String,
    var favouriteId: String = "",
    val breed: String,
    val imageUrl: String,
    val imageId: String,
    val lifeSpan: String,
) {
    companion object {
        fun emptyFavouriteCatDomainModel() = FavouriteCatDomainModel(
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }
}

data class FavouriteInfoDomainModel(
    val favouriteId: String = "",
    val imageId: String = "",
)
