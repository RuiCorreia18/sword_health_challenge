package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.Model.FavouriteCat
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CatRepository {
    fun getCatList(): Single<List<Cat>>
    fun searchCat(search: String): Single<List<Cat>>
    fun postFavouriteCat(imageId: String): Completable
    fun getFavouriteCats(): Single<List<String>>
    fun getCatByImageId(imageId: String): Single<FavouriteCat>
}