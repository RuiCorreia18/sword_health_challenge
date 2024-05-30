package com.example.swordhealthchallenge.di

import android.app.Application
import com.example.swordhealthchallenge.MainApplication
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
        AppBindModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun inject(frag: CatsListFragment)
    fun inject(frag: FavouritesFragment)
    fun inject(activity: DetailsActivity)
    fun inject(app: MainApplication)
}
