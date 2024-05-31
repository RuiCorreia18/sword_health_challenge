package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.usecases.GetCatDetailsUseCase
import com.example.swordhealthchallenge.utils.CatDetailsDomainModelFakes.catDetailsDomainModelFav1
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetCatDetailsUseCaseTest {

    private val repository: CatRepository = mockk()
    private val useCase = GetCatDetailsUseCase(repository)

    @Test
    fun `when getCatDetails is success should return CatDetails`() {
        val catId = "CatId1"

        every { repository.getCatDetails(catId) } returns Single.just(catDetailsDomainModelFav1)

        useCase(catId)
            .test()
            .assertResult(catDetailsDomainModelFav1)
    }
}