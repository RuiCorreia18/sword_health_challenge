package com.example.swordhealthchallenge.data.remote

import com.example.swordhealthchallenge.data.remote.model.CatByImageResponse
import com.example.swordhealthchallenge.data.remote.model.CatDetailsResponse
import com.example.swordhealthchallenge.data.remote.model.CatResponse
import com.example.swordhealthchallenge.data.remote.model.FavouriteCatBody
import com.example.swordhealthchallenge.data.remote.model.FavouriteCatResponse
import com.example.swordhealthchallenge.data.remote.model.PostFavouriteResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val catApi: CatApi
) {
    // page
    fun getCatList(): Single<List<CatResponse>> = catApi.getCatList()
    fun searchCat(search: String): Single<List<CatResponse>> = catApi.searchCat(search)
    fun postFavouriteCat(favouriteCat: FavouriteCatBody): Single<PostFavouriteResponse> = catApi.postFavouriteCat(favouriteCat)
    fun getFavouriteCats(): Single<List<FavouriteCatResponse>> = catApi.getFavouriteCats()
    fun getCatImage(imageId: String): Single<CatByImageResponse> = catApi.getCatByImageId(imageId)
    fun getCatDetails(catId: String): Single<CatDetailsResponse> = catApi.getCatDetails(catId)
    fun deleteFavouriteCat(favouriteId: String): Completable = catApi.deleteFavourite(favouriteId)
}
