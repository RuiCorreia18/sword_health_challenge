package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatResponse
import com.example.swordhealthchallenge.domain.Model.Cat
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val catApi: CatApi
) {

    fun getCatList(): Single<List<CatResponse>> = catApi.getCatList()

}