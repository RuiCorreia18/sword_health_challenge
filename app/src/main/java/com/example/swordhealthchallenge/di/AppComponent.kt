package com.example.swordhealthchallenge.di

import android.content.Context
import com.example.swordhealthchallenge.di.viewModel.ViewModelModule
import com.example.swordhealthchallenge.ui.catsList.CatsListFragment
import com.example.swordhealthchallenge.ui.details.DetailsActivity
import com.example.swordhealthchallenge.ui.favourites.FavouritesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ViewModelModule::class,
        AppModule::class,
    ]
)
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(frag: CatsListFragment)
    fun inject(frag: FavouritesFragment)
    fun inject(activity: DetailsActivity)
}
