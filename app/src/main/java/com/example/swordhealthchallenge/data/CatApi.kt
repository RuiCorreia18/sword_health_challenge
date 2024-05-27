package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.BuildConfig
import com.example.swordhealthchallenge.data.entities.CatByImageResponse
import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.data.entities.FavouriteCatBody
import com.example.swordhealthchallenge.data.entities.FavouriteCatResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CatApi {

    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @GET("breeds")
    fun getCatList(
        @Query("limit") limit: String = "20",
    ): Single<List<CatResponse>>


    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @GET("breeds/search")
    fun searchCat(@Query("q") search: String): Single<List<CatResponse>>


    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @GET("favourites")
    fun getFavouriteCats(): Single<List<FavouriteCatResponse>>


    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @POST("favourites")
    fun postFavouriteCat(@Body imageId: FavouriteCatBody): Completable

    @GET("images/{imageId}")
    fun getCatByImageId(@Path("imageId") imageId: String): Single<CatByImageResponse>

}