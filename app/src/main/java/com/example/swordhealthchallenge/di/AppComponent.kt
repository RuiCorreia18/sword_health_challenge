package com.example.swordhealthchallenge.di

import android.content.Context
import com.example.swordhealthchallenge.di.ViewModel.ViewModelModule
import com.example.swordhealthchallenge.ui.catslist.CatsListFragment
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
}