package com.example.swordhealthchallenge.data

import com.example.swordhealthchallenge.data.entities.local.CatEntity
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val catDao: CatDao
) {

    fun getAllCats(): Single<List<CatEntity>> {
        return catDao.getAllCats()
    }

    fun saveCats(catsList: List<CatDomainModel>): Completable {
        return Completable.fromAction {
            // Convert CatDomainModel to CatEntity and save to Room
            val catEntities = catsList.map { it.toEntity() }
            catDao.insertCats(catEntities)
        }
    }
}
