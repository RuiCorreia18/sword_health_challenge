package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatByImageResponse
import com.example.swordhealthchallenge.data.entities.CatDetailsResponse
import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.data.entities.FavouriteCatBody
import com.example.swordhealthchallenge.data.entities.FavouriteCatResponse
import com.example.swordhealthchallenge.data.entities.PostFavouriteResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CatApi {

    @GET("breeds")
    fun getCatList(
        @Query("limit") limit: String = "20",
    ): Single<List<CatResponse>>

    @GET("breeds/search")
    fun searchCat(@Query("q") search: String): Single<List<CatResponse>>

    @GET("favourites")
    fun getFavouriteCats(): Single<List<FavouriteCatResponse>>

    @POST("favourites")
    fun postFavouriteCat(@Body imageId: FavouriteCatBody): Single<PostFavouriteResponse>

    @GET("images/{imageId}")
    fun getCatByImageId(@Path("imageId") imageId: String): Single<CatByImageResponse>

    @GET("breeds/{breedsId}")
    fun getCatDetails(@Path("breedsId") catId: String): Single<CatDetailsResponse>

    @DELETE("favourites/{favouriteId}")
    fun deleteFavourite(@Path("favouriteId") favouriteId: String): Completable
}
