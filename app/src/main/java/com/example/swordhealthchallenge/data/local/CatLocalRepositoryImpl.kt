package com.example.swordhealthchallenge.data.local

import com.example.swordhealthchallenge.data.local.entity.CatEntity
import com.example.swordhealthchallenge.data.remote.model.CatResponse
import com.example.swordhealthchallenge.domain.CatLocalRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CatLocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : CatLocalRepository {
    override fun getAllCats(): Single<List<CatEntity>> {
        return localDataSource.getAllCats()
    }

    override fun saveCats(catsList: List<CatResponse>): Completable {
        return localDataSource.saveCats(catsList)
    }
}
