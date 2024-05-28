package com.example.swordhealthchallenge.ui.details

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.domain.Model.CatDetails
import com.example.swordhealthchallenge.domain.usecases.GetCatDetailsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getCatDetailsUseCase: GetCatDetailsUseCase
) : ViewModel() {

    private val _catDetails = MutableLiveData<CatDetails>()
    val catDetails: LiveData<CatDetails>
        get() = _catDetails

    @SuppressLint("CheckResult")
    fun getCatDetails(catId: String, catImageUrl: String, catFavouriteId: String) {
        getCatDetailsUseCase.getCatDetails(catId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _catDetails.value = it.copy(
                        imageUrl = catImageUrl,
                        favouriteId = catFavouriteId
                    )
                }
            )
    }
}