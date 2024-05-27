package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.FavouriteCatBody
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.Model.CatDetails
import com.example.swordhealthchallenge.domain.Model.FavouriteCat
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
                res.toDomainList()
            }
    }

    override fun postFavouriteCat(imageId: String): Completable =
        remoteDataSource.postFavouriteCat(FavouriteCatBody(imageId))

    override fun getFavouriteCats(): Single<List<String>> {
        return remoteDataSource.getFavouriteCats()
            .map { res ->
                res.map { it.image_id }.distinct()
            }
    }

    override fun getCatByImageId(imageId: String): Single<FavouriteCat> {
        return remoteDataSource.getCatImage(imageId)
            .map { res ->
                res.toDomainModel()
            }
    }

    override fun getCatDetails(catId: String): Single<CatDetails> {
        return remoteDataSource.getCatDetails(catId)
            .map { res ->
                res.toDomainModel()
            }
    }
}
