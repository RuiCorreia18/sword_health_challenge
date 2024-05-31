package com.example.swordhealthchallenge.utils

import com.example.swordhealthchallenge.data.remote.model.CatImageResponse
import com.example.swordhealthchallenge.data.remote.model.CatResponse

object CatResponseFakes {
    val catResponseMock1 = CatResponse(
        id = "CatId1",
        name = "Breed1",
        image = CatImageResponse(
            url = "URL1",
            id = "ImageId1",
        ),
        temperament = "Temperament1",
        origin = "Origin1",
        description = "Description1",
        life_span = "LifeSpan1",
    )
    val catResponseMock2 = CatResponse(
        id = "CatId2",
        name = "Breed2",
        image = CatImageResponse(
            url = "URL2",
            id = "ImageId2",
        ),
        temperament = "Temperament2",
        origin = "Origin2",
        description = "Description2",
        life_span = "LifeSpan1",
    )
}