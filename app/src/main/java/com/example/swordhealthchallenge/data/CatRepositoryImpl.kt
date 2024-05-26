package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.Model.Cat
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CatRepository {

    override fun getCatList(): Single<List<Cat>> {
        return remoteDataSource.getCatList()
            .map { res ->
                res.toDomainList()
            }
    }

    override fun getCatImage(imageId: String): Single<String> {
        return remoteDataSource.getCatImage(imageId).map {
            it.url
        }
    }
}