package com.example.swordhealthchallenge.ui.favourites

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Model.FavouriteCat
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
            .flatMap { catUrlList ->
                //converts to Observable sequence that emits URL one by one
                Observable.fromIterable(catUrlList)
                    .flatMapSingle { catImageUrl ->
                        getCatListUseCase.getCatByImageId(catImageUrl)
                            .subscribeOn(Schedulers.io())
                            .onErrorResumeNext {
                                Log.e("ERROR CAT API IMAGE", "url:${catImageUrl} $it")
                                Single.just(FavouriteCat())
                            }
                    }
                    //Collects all to a List
                    .toList()
                    .map { it.filter { cat -> cat != FavouriteCat() } }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { favouriteCats ->
                    _favouriteCatsList.value = favouriteCats
                },
                onError = { error ->
                    Log.e("ERROR CAT API FAV", error.toString())
                }
            )
            .addTo(compositeDisposable)
    }


}