package com.example.swordhealthchallenge.ui.favourites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.swordhealthchallenge.domain.model.FavouriteInfoDomainModel
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetFavouriteCatsUseCase
import com.example.swordhealthchallenge.ui.utils.FavouritesDomainModelFakes.favCat1
import com.example.swordhealthchallenge.ui.utils.FavouritesDomainModelFakes.favCat2
import com.example.swordhealthchallenge.ui.utils.FavouritesDomainModelFakes.favInfo1
import com.example.swordhealthchallenge.ui.utils.FavouritesDomainModelFakes.favInfo2
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FavouritesViewModelTest {

    private val getFavouriteCatsUseCase: GetFavouriteCatsUseCase = mockk()
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase = mockk()
    private val ioSchedulers: Scheduler = Schedulers.trampoline()
    private val mainSchedulers: Scheduler = Schedulers.trampoline()

    private val viewModel = FavouritesViewModel(
        getFavouriteCatsUseCase,
        deleteFavouriteCatUseCase,
        ioSchedulers,
        mainSchedulers
    )

    @JvmField
    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when getFavouriteCats success should fill livedata`() {
        val favListMock = listOf(favInfo1) // Create fake list

        val expected = listOf(favCat1)
        fillFavouriteCatsListLiveData(favListMock)

        assertEquals(expected, viewModel.favouriteCatsList.value)
    }

    @Test
    fun `when deleteFavouriteCat success should remove from livedata`() {
        val favListMock = listOf(favInfo1, favInfo2) // Create fake list
        val favId = "FavId1"

        val expected = listOf(favCat2)

        every { deleteFavouriteCatUseCase(favId) } returns Completable.complete()
        every { getFavouriteCatsUseCase.getCatByImageId(favInfo2.imageId) } returns Single.just(favCat2)
        fillFavouriteCatsListLiveData(favListMock)

        viewModel.deleteFavouriteCat(favId)

        assertEquals(expected, viewModel.favouriteCatsList.value)
    }

    private fun fillFavouriteCatsListLiveData(fakeFavouriteList: List<FavouriteInfoDomainModel>) {
        every { getFavouriteCatsUseCase() } returns Single.just(fakeFavouriteList)
        every { getFavouriteCatsUseCase.getCatByImageId(favInfo1.imageId) } returns Single.just(favCat1)

        viewModel.getFavouriteCats()
    }
}
