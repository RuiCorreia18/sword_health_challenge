package com.example.swordhealthchallenge.ui.favourites


import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import com.example.swordhealthchallenge.ui.utils.FavouritesDomainModelFakes.favCat1
import com.example.swordhealthchallenge.ui.utils.FavouritesDomainModelFakes.favInfo1
import com.example.swordhealthchallenge.ui.utils.RxUnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FavouritesViewModelTest: RxUnitTest() {


    private val getCatListUseCase: GetCatListUseCase = mockk()
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase = mockk()
    private lateinit var viewModel: FavouritesViewModel

    @Before
    fun setup() {
        viewModel =
            FavouritesViewModel(
                getCatListUseCase,
                deleteFavouriteCatUseCase
            )
    }

    @Test
    fun `when getFavouriteCats success should return list`() {
        val favInfoList =
            listOf(favInfo1)

        every { getCatListUseCase.getFavouriteCats() } returns Single.just(favInfoList)
        every { getCatListUseCase.getCatByImageId(any()) } returns Single.just(favCat1)

        viewModel.getFavouriteCats()

        verify {

        }
        assertEquals(viewModel.favouriteCatsList, listOf(favCat1))
    }

    @Test
    fun `when deleteFavouriteCat success should remove cat`() {
        val favInfoList =
            listOf(favInfo1)

        every { getCatListUseCase.getFavouriteCats() } returns Single.just(favInfoList)
        every { getCatListUseCase.getCatByImageId(any()) } returns Single.just(favCat1)

        viewModel.getFavouriteCats()

        verify {

        }
        assertEquals(viewModel.favouriteCatsList, listOf(favCat1))
    }
}