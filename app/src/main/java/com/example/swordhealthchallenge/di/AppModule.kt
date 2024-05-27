package com.example.swordhealthchallenge.di

import com.example.swordhealthchallenge.BuildConfig
import com.example.swordhealthchallenge.data.CatApi
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import com.example.swordhealthchallenge.domain.usecases.PostFavouriteCatUseCase
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    fun provideRetrofit(): CatApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CatApi::class.java)
    }

    @Provides
    fun providesGetCatListUseCase(repository: CatRepository) = GetCatListUseCase(repository)
    @Provides
    fun providesPostFavouriteCatUseCase(repository: CatRepository) = PostFavouriteCatUseCase(repository)
}