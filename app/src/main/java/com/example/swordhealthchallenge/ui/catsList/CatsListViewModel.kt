package com.example.swordhealthchallenge.ui.catsList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.model.CatDomainModel
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class CatsListViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase,
    private val postFavouriteCatUseCase: PostFavouriteCatUseCase,
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase,
    @Named("io") private val ioSchedulers: Scheduler,
    @Named("main") private val mainSchedulers: Scheduler,
) : ViewModel() {
    private val _catsList = MutableLiveData<List<CatDomainModel>>(emptyList())
    val catsList: LiveData<List<CatDomainModel>>
        get() = _catsList

    private val tempCatsList = mutableListOf<CatDomainModel>()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun getCatList() {
        tempCatsList.clear()
        getCatListUseCase.getCatList()
            .subscribeOn(ioSchedulers)
            .observeOn(mainSchedulers)
            .subscribeBy(
                onSuccess = { catList ->
                    // TODO Added this in case i got time for pagination
                    catsList.value?.let { _catsList.value = it.plus(catList) }
                },
                onError = {
                    updateError("ERROR CAT API BREED", it, "Problem getting cats list")
                }
            )
            .addTo(compositeDisposable)
    }

    fun searchCats(catSearch: String) {
        getCatListUseCase.searchCat(catSearch)
            .subscribeOn(ioSchedulers)
            .observeOn(mainSchedulers)
            .subscribeBy(
                onSuccess = { _catsList.value = it },
                onError = {
                    updateError("ERROR CAT API BREED", it, "Problem searching for Cats")
                }
            )
            .addTo(compositeDisposable)
    }

    fun filterCatWithLocalSearch(catSearch: String) {
        if (tempCatsList.isEmpty()) catsList.value?.let { tempCatsList.addAll(it) }
        _catsList.value = tempCatsList.filter {
            it.breed.lowercase().contains(catSearch.lowercase())
        }
    }

    fun favouriteCat(imageId: String) {
        postFavouriteCatUseCase.postFavouriteCat(imageId)
            .subscribeOn(ioSchedulers)
            .observeOn(mainSchedulers)
            .subscribeBy(
                onSuccess = {
                    updateCatListWithFavourite(imageId, it)
                },
                onError = {
                    updateError("ERROR FAVOURITE CAT", it, "Problem saving favourite cat")
                }
            )
            .addTo(compositeDisposable)
    }

    private fun updateCatListWithFavourite(imageId: String, favId: String) {
        val tempCatList = catsList.value.orEmpty()
        tempCatList.find { cat -> cat.imageId == imageId }?.favouriteId = favId
        _catsList.value = tempCatList
    }

    fun deleteFavouriteCat(favouriteId: String) {
        deleteFavouriteCatUseCase.deleteFavouriteCat(favouriteId)
            .subscribeOn(ioSchedulers)
            .observeOn(mainSchedulers)
            .subscribeBy(
                onComplete = {
                    updateCatListNoFavourite(favouriteId)
                },
                onError = {
                    updateError("ERROR DELETE FAVOURITE CAT", it, "Problem deleting favourite cat")
                }
            )
            .addTo(compositeDisposable)
    }

    private fun updateCatListNoFavourite(favouriteId: String) {
        val tempCatList = catsList.value.orEmpty()
        tempCatList.find { cat -> cat.favouriteId == favouriteId }?.favouriteId = ""
        _catsList.value = tempCatList
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
