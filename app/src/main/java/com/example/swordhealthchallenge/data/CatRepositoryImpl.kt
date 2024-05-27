package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.FavouriteCat
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.Model.Cat
import io.reactivex.rxjava3.core.Completable
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

    override fun searchCat(search: String): Single<List<Cat>> {
        return remoteDataSource.searchCat(search)
            .map { res ->
                res.toDomainList() }
    }

    override fun postFavouriteCat(imageId: String): Completable {
        return remoteDataSource.postFavouriteCat(
            FavouriteCat(imageId)
        ).doOnComplete {

        }
    }
}
