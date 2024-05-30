package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.data.remote.model.CatResponse
import com.example.swordhealthchallenge.domain.model.CatDetailsDomainModel
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CatRepository {
    fun getCatList(): Single<List<CatResponse>>
    fun searchCat(search: String): Single<List<CatDomainModel>>
    fun postFavouriteCat(imageId: String): Single<String>
    fun getFavouriteCats(): Single<List<FavouriteInfoDomainModel>>
    fun getCatByImageId(imageId: String): Single<FavouriteCatDomainModel>
    fun getCatDetails(catId: String): Single<CatDetailsDomainModel>
    fun deleteFavouriteCat(favouriteId: String): Completable
}
