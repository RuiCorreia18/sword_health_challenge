package com.example.swordhealthchallenge.domain

import com.example.swordhealthchallenge.domain.usecases.GetFavouriteCatsUseCase
import com.example.swordhealthchallenge.utils.FavouritesDomainModelFakes.favCat1
import com.example.swordhealthchallenge.utils.FavouritesDomainModelFakes.favInfo1
import com.example.swordhealthchallenge.utils.FavouritesDomainModelFakes.favInfo2
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetFavouriteCatsUseCaseTest {

    private val repository: CatRepository = mockk()
    private val localRepository: CatLocalRepository = mockk()
    private val useCase = GetFavouriteCatsUseCase(repository, localRepository)

    @Test
    fun `when getFavouriteCats is success should return list of FavouriteInfo`() {
        val catFavInfoListMock = listOf(favInfo1, favInfo2)

        every { repository.getFavouriteCats() } returns Single.just(catFavInfoListMock)
        every { localRepository.setFavouriteCat(any(), any()) } returns Completable.complete()

        useCase()
            .test()
            .assertResult(catFavInfoListMock)
    }

    @Test
    fun `when getCatByImageId is success should return list of FavouriteCat`() {
        val imageId = "ImageId1"

        every { repository.getCatByImageId(imageId) } returns Single.just(favCat1)

        useCase.getCatByImageId(imageId)
            .test()
            .assertResult(favCat1)
    }
}