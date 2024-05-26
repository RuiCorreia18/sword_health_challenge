package com.example.swordhealthchallenge.ui.catslist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Model.Cat
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CatsListViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase
) : ViewModel() {
    private val _catsList = MutableLiveData<List<Cat>>()
    val catsList: LiveData<List<Cat>>
        get() = _catsList

    @SuppressLint("CheckResult")
    fun getCatList() {
        val tempList = mutableListOf<Cat>()
        catsList.value?.let { tempList.addAll(it) }

        //TODO check if better way to do this
        getCatListUseCase.getCatList()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { catList ->
                    catList.forEach { cat ->
                        getCatListUseCase.getCatImage(cat.imageUrl)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeBy(
                                onSuccess = {
                                    cat.imageUrl = it
                                    tempList.add(cat)
                                    if(catList.last() == cat){
                                        _catsList.value = tempList
                                    }
                                },
                                onError = {
                                    Log.e("ERROR CAT API IMAGE", it.toString())
                                }
                            )
                    }
                },
                onError = {
                    Log.e("ERROR CAT API BREED", it.toString())
                }
            )
            .addTo(CompositeDisposable())


    }

    /*@SuppressLint("CheckResult")
    private fun getCatImage(imageId: String): String {
        return getCatListUseCase.getCatImage(imageId)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess={
                    it
                }
            )
    }*/
}