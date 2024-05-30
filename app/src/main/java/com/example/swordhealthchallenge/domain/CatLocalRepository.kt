package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.data.entities.local.CatEntity
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CatLocalRepository {

    fun getAllCats(): Single<List<CatEntity>>
    fun saveCats(catsList: List<CatDomainModel>): Completable
}