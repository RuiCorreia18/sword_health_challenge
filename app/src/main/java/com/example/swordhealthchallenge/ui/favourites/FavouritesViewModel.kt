package com.example.swordhealthchallenge.ui.favourites

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Model.FavouriteCat
import com.example.swordhealthchallenge.domain.usecases.DeleteFavouriteCatUseCase
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase,
    private val deleteFavouriteCatUseCase: DeleteFavouriteCatUseCase,
) : ViewModel() {

    private val _favouriteCatsList = MutableLiveData<List<FavouriteCat>>(emptyList())
    val favouriteCatsList: LiveData<List<FavouriteCat>>
        get() = _favouriteCatsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val compositeDisposable by lazy { CompositeDisposable() }

    @SuppressLint("CheckResult")
    fun getFavouriteCats() {
        getCatListUseCase.getFavouriteCats()
            .subscribeOn(Schedulers.io())
            .flatMap { favouriteList ->
                //converts to Observable sequence that emits URL one by one
                Observable.fromIterable(favouriteList)
                    .flatMapSingle { favourite ->
                        getCatListUseCase.getCatByImageId(favourite.imageId)
                            .subscribeOn(Schedulers.io())
                            .onErrorResumeNext {
                                Log.e("ERROR CAT API IMAGE", "url:${favourite.imageId} $it")
                                Single.just(FavouriteCat())
                            }
                            .map {
                                if (it.imageUrl.isNotEmpty()) {
                                    it.copy(favouriteId = favourite.favouriteId)
                                }else {
                                    it
                                }
                            }
                    }
                    //Collects all to a List
                    .toList()
                    .map { it.filter { cat -> cat != FavouriteCat() } }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { favouriteCats ->
                    _favouriteCatsList.value = favouriteCats.sortedBy { it.breed }
                },
                onError = { error ->
                    Log.e("ERROR CAT API FAV", error.toString())
                }
            )
            .addTo(compositeDisposable)
    }

    @SuppressLint("CheckResult")
    fun deleteFavouriteCat(favouriteId: String) {
        deleteFavouriteCatUseCase.deleteFavouriteCat(favouriteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    val tempCatList = favouriteCatsList.value!!.toMutableList()
                    tempCatList.removeIf { it.favouriteId == favouriteId }
                    _favouriteCatsList.value = tempCatList
                },
                onError = {
                    Log.e("ERROR DELETE FAVOURITE CAT", it.toString())
                    //_errorMessage.value = "Problem on Favourite Cat"
                }
            )
    }


}