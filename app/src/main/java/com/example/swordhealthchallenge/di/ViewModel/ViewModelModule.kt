package com.example.swordhealthchallenge.di.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swordhealthchallenge.ui.catslist.CatsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CatsListViewModel::class)
    abstract fun bindFeedViewModel(viewModel: CatsListViewModel): ViewModel
}