package com.example.swordhealthchallenge.data.local

import com.example.swordhealthchallenge.data.local.entity.CatEntity
import com.example.swordhealthchallenge.data.remote.model.CatResponse
import com.example.swordhealthchallenge.data.toEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val catDao: CatDao
) {

    fun getAllCats(): Single<List<CatEntity>> = catDao.getAllCats()

    fun saveCats(catsList: List<CatResponse>): Completable {
        return Completable.fromAction {
            // Convert CatDomainModel to CatEntity and save to Room
            val catEntities = catsList.map { it.toEntity() }

            val existingCats = catDao.getAllCats().blockingGet()
            val existingCatsMap = existingCats.associateBy { it.id }

            val catsToInsert = mutableListOf<CatEntity>()
            val catsToUpdate = mutableListOf<CatEntity>()

            for (cat in catEntities) {
                if (existingCatsMap.containsKey(cat.id)) {
                    // Cat exists in the database, add it to the list to update
                    catsToUpdate.add(cat)
                } else {
                    // Cat doesn't exist in the database, add it to the list to insert
                    catsToInsert.add(cat)
                }
            }

            // Perform batch insert and update operations
            if (catsToInsert.isNotEmpty()) {
                catDao.insertCats(catsToInsert)
            }

            catsToUpdate.forEach { cat ->
                updateCatInfo(cat)
            }
        }
    }

    private fun updateCatInfo(cat: CatEntity) {
        catDao.updateCatInfo(
            id = cat.id,
            breed = cat.breed,
            imageUrl = cat.imageUrl,
            imageId = cat.imageId,
            origin = cat.origin,
            temperament = cat.temperament,
            description = cat.description
        )
    }

    fun setFavouriteCat(imageId: String, favouriteId: String): Completable {
        return Completable.fromAction {
            catDao.setFavouriteCat(imageId, favouriteId)
        }
    }

    fun deleteFavouriteCat(favouriteId: String): Completable {
        return Completable.fromAction {
            catDao.deleteFavouriteCat(favouriteId)
        }
    }

    fun getFavouriteCats(): Single<List<CatEntity>> = catDao.getFavouriteCats()
}



