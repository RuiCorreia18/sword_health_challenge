package com.example.swordhealthchallenge.di

import com.example.swordhealthchallenge.data.CatApi
import com.example.swordhealthchallenge.domain.CatRepository
import com.example.swordhealthchallenge.domain.usecases.GetCatListUseCase
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.thecatapi.com/v1/"

@Module
class AppModule {

    @Provides
    fun provideRetrofit(): CatApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CatApi::class.java)
    }

    @Provides
    fun providesGetCatListUseCase(repository: CatRepository) = GetCatListUseCase(repository)
}