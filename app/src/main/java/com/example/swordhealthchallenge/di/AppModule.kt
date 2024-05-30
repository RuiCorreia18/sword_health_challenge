package com.example.swordhealthchallenge.di

import android.app.Application
import androidx.room.Room
import com.example.swordhealthchallenge.BuildConfig
import com.example.swordhealthchallenge.data.AppDatabase
import com.example.swordhealthchallenge.data.CatApi
import com.example.swordhealthchallenge.data.CatDao
import com.example.swordhealthchallenge.data.CatRepositoryImpl
import com.example.swordhealthchallenge.domain.CatLocalRepository
import com.example.swordhealthchallenge.domain.CatRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("x-api-key", BuildConfig.API_KEY)
                .build()
            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): CatApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CatApi::class.java)
    }

    @Provides
    @Named("io")
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("main")
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "cat-database"
        ).build()
    }

    @Provides
    fun provideCatDao(appDatabase: AppDatabase): CatDao {
        return appDatabase.catDao()
    }
}

@Module
abstract class AppBindModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: CatRepositoryImpl): CatRepository

    @Binds
    abstract fun bindLocalRepository(repositoryImpl: CatRepositoryImpl): CatLocalRepository
}
