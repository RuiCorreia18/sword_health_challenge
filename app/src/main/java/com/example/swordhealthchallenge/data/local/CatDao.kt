package com.example.swordhealthchallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.swordhealthchallenge.data.local.entity.CatEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CatDao {

    @Query("SELECT * FROM cats")
    fun getAllCats(): Single<List<CatEntity>>

    @Insert
    fun insertCats(catsList: List<CatEntity>)

    @Query(
        "UPDATE cats SET " +
            "breed = :breed," +
            " imageUrl = :imageUrl," +
            " imageId = :imageId," +
            " origin = :origin," +
            " temperament = :temperament," +
            " description = :description" +
            " WHERE id = :id"
    )
    fun updateCatInfo(
        id: String,
        breed: String,
        imageUrl: String,
        imageId: String,
        origin: String,
        temperament: String,
        description: String,
    )
}
