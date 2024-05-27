package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatByImageResponse
import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.data.entities.FavouriteCatBody
import com.example.swordhealthchallenge.data.entities.FavouriteCatResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val catApi: CatApi
) {
    //page
    fun getCatList(): Single<List<CatResponse>> = catApi.getCatList()
    fun searchCat(search: String): Single<List<CatResponse>> = catApi.searchCat(search)
    fun postFavouriteCat(favouriteCat: FavouriteCatBody): Completable = catApi.postFavouriteCat(favouriteCat)
    fun getFavouriteCats(): Single<List<FavouriteCatResponse>> = catApi.getFavouriteCats()
    fun getCatImage(imageId: String): Single<CatByImageResponse> = catApi.getCatByImageId(imageId)

}