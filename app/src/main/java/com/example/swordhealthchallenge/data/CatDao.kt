package com.example.swordhealthchallenge.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.swordhealthchallenge.data.entities.local.CatEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CatDao {

    @Query("SELECT * FROM cats")
    fun getAllCats(): Single<List<CatEntity>>

    @Insert
    fun insertCats(catsList: List<CatEntity>)
}