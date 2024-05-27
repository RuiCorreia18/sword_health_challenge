package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.CatResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val catApi: CatApi
) {
    //page
    fun getCatList(): Single<List<CatResponse>> = catApi.getCatList()
    fun searchCat(search: String): Single<List<CatResponse>> = catApi.searchCat(search)

}