package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.domain.Model.Cat
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CatApi {

    @GET("breeds")
    fun getCatList(): Single<List<CatResponse>>
}