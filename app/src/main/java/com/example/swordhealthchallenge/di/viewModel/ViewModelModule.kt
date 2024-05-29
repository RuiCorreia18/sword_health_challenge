package com.example.swordhealthchallenge.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swordhealthchallenge.data.CatRepositoryImpl
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.ui.catsList.CatsListViewModel
import com.example.swordhealthchallenge.ui.details.DetailsViewModel
import com.example.swordhealthchallenge.ui.favourites.FavouritesViewModel
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
    abstract fun bindCatListViewModel(viewModel: CatsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    abstract fun bindFavouriteViewModel(viewModel: FavouritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    abstract fun bindRepository(repositoryImpl: CatRepositoryImpl): CatRepository
}
