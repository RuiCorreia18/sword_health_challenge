package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.model.Cat
import com.example.swordhealthchallenge.domain.model.CatDetails
import com.example.swordhealthchallenge.domain.model.FavouriteCat
import com.example.swordhealthchallenge.domain.model.FavouriteInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CatRepository {
    fun getCatList(): Single<List<Cat>>
    fun searchCat(search: String): Single<List<Cat>>
    fun postFavouriteCat(imageId: String): Single<String>
    fun getFavouriteCats(): Single<List<FavouriteInfo>>
    fun getCatByImageId(imageId: String): Single<FavouriteCat>
    fun getCatDetails(catId: String): Single<CatDetails>
    fun deleteFavouriteCat(favouriteId: String): Completable
}
