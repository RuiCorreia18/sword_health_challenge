package com.example.swordhealthchallenge.data.remote

import com.example.swordhealthchallenge.data.remote.model.CatResponse
import com.example.swordhealthchallenge.data.remote.model.FavouriteCatBody
import com.example.swordhealthchallenge.data.toDomainList
import com.example.swordhealthchallenge.data.toDomainModel
import com.example.swordhealthchallenge.data.toDomainModelList
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.model.CatDetailsDomainModel
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CatRepository {

    override fun getCatList(): Single<List<CatResponse>> {
        return remoteDataSource.getCatList()
    }

    override fun searchCat(search: String): Single<List<CatDomainModel>> {
        return remoteDataSource.searchCat(search)
            .map { res ->
                res.toDomainList()
            }
    }

    override fun postFavouriteCat(imageId: String): Single<String> =
        remoteDataSource.postFavouriteCat(FavouriteCatBody(imageId)).map {
            it.id
        }

    override fun getFavouriteCats(): Single<List<FavouriteInfoDomainModel>> {
        return remoteDataSource.getFavouriteCats()
            .map { res ->
                res.toDomainModelList().distinctBy { it.imageId }
            }
    }

    override fun getCatByImageId(imageId: String): Single<FavouriteCatDomainModel> {
        return remoteDataSource.getCatImage(imageId)
            .map { res ->
                res.toDomainModel()
            }
    }

    override fun getCatDetails(catId: String): Single<CatDetailsDomainModel> {
        return remoteDataSource.getCatDetails(catId)
            .map { res ->
                res.toDomainModel()
            }
    }

    override fun deleteFavouriteCat(favouriteId: String): Completable {
        return remoteDataSource.deleteFavouriteCat(favouriteId)
    }
}
