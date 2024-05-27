package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.BuildConfig
import com.example.swordhealthchallenge.data.entities.CatResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApi {

    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @GET("breeds")
    fun getCatList(
        @Query("limit") limit: String = "20",
    ): Single<List<CatResponse>>
}