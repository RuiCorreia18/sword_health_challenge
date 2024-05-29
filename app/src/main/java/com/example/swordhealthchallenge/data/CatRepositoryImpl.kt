package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.FavouriteCatBody
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.Cat
import com.example.swordhealthchallenge.domain.model.CatDetails
import com.example.swordhealthchallenge.domain.model.FavouriteCat
import com.example.swordhealthchallenge.domain.model.FavouriteInfo
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

    override fun postFavouriteCat(imageId: String): Single<String> =
        remoteDataSource.postFavouriteCat(FavouriteCatBody(imageId)).map {
            it.id
        }

    override fun getFavouriteCats(): Single<List<FavouriteInfo>> {
        return remoteDataSource.getFavouriteCats()
            .map { res ->
                res.toDomainModelList().distinctBy { it.imageId }
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

    override fun deleteFavouriteCat(favouriteId: String): Completable {
        return remoteDataSource.deleteFavouriteCat(favouriteId)
    }
}
