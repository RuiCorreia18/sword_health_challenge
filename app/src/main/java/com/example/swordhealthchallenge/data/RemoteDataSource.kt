package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatByImageResponse
import com.example.swordhealthchallenge.data.entities.CatDetailsResponse
import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.data.entities.FavouriteCatBody
import com.example.swordhealthchallenge.data.entities.FavouriteCatResponse
import com.example.swordhealthchallenge.data.entities.PostFavouriteResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val catApi: CatApi
) {
    //page
    fun getCatList(): Single<List<CatResponse>> = catApi.getCatList()
    fun searchCat(search: String): Single<List<CatResponse>> = catApi.searchCat(search)
    fun postFavouriteCat(favouriteCat: FavouriteCatBody): Single<PostFavouriteResponse> = catApi.postFavouriteCat(favouriteCat)
    fun getFavouriteCats(): Single<List<FavouriteCatResponse>> = catApi.getFavouriteCats()
    fun getCatImage(imageId: String): Single<CatByImageResponse> = catApi.getCatByImageId(imageId)
    fun getCatDetails(catId: String): Single<CatDetailsResponse> = catApi.getCatDetails(catId)

}