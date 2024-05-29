package com.example.swordhealthchallenge.ui.favourites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.model.FavouriteCat
import com.example.swordhealthchallenge.domain.model.FavouriteInfo
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class FavouritesViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase,
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase,
    @Named("io") private val ioSchedulers: Scheduler,
    @Named("main") private val mainSchedulers: Scheduler,
) : ViewModel() {

    private val _favouriteCatsList = MutableLiveData<List<FavouriteCat>>(emptyList())
    val favouriteCatsList: LiveData<List<FavouriteCat>>
        get() = _favouriteCatsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun getFavouriteCats() {
        getCatListUseCase.getFavouriteCats()
            .subscribeOn(ioSchedulers)
            .flattenAsObservable { infoList -> infoList }
            .flatMapSingle { info -> fetchCatByImageId(info) }
            .filter { it != FavouriteCat() }
            .toList()
            .observeOn(mainSchedulers)
            .subscribeBy(
                onSuccess = { favouriteCats ->
                    _favouriteCatsList.value = favouriteCats.sortedBy { it.breed }
                },
                onError = {
                    updateError(
                        "ERROR CAT API FAV",
                        it,
                        "Problem getting favourite cat list"
                    )
                }
            )
            .addTo(compositeDisposable)
    }

    private fun fetchCatByImageId(info: FavouriteInfo) =
        getCatListUseCase.getCatByImageId(info.imageId)
            .onErrorResumeNext { throwable ->
                Log.e("ERROR CAT API IMAGE", "url:${info.imageId} $throwable")
                // Added since i messed with api on postman i created some wrong data
                Single.just(FavouriteCat())
            }
            .map { cat ->
                if (cat.imageUrl.isNotEmpty()) cat.copy(favouriteId = info.favouriteId) else cat
            }

    fun deleteFavouriteCat(favouriteId: String) {
        deleteFavouriteCatUseCase.deleteFavouriteCat(favouriteId)
            .subscribeOn(ioSchedulers)
            .observeOn(mainSchedulers)
            .subscribeBy(
                onComplete = {
                    updateListWithoutFavourite(favouriteId)
                },
                onError = {
                    updateError("ERROR DELETE FAVOURITE CAT", it, "Problem deleting favourite cat")
                }
            )
            .addTo(compositeDisposable)
    }

    private fun updateListWithoutFavourite(favouriteId: String) {
        val tempCatList = favouriteCatsList.value.orEmpty().toMutableList()
        tempCatList.removeIf { it.favouriteId == favouriteId }
        _favouriteCatsList.value = tempCatList
    }

    private fun updateError(tag: String, throwable: Throwable, errorMessage: String) {
        Log.e(tag, throwable.toString())
        _errorMessage.value = errorMessage
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
