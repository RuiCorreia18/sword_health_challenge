package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.data.local.entity.CatEntity
import com.example.swordhealthchallenge.data.remote.model.CatResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CatLocalRepository {

    fun getAllCats(): Single<List<CatEntity>>
    fun saveCats(catsList: List<CatResponse>): Completable
    fun setFavouriteCat(imageId: String, favouriteId: String): Completable
    fun deleteFavouriteCat(favouriteId: String): Completable
    fun getFavouriteCats(): Single<List<CatEntity>>
}