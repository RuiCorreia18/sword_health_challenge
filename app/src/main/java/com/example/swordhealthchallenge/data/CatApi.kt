package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatImageResponse
import com.example.swordhealthchallenge.data.entities.CatResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatApi {

    @GET("breeds")
    fun getCatList(@Query("limit") limit: String = "20"): Single<List<CatResponse>>

    @GET("images/{imageId}")
    fun getCatImage(@Path("imageId") imageId: String): Single<CatImageResponse>
}