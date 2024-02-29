package com.example.newsnow.dependency_Injection

import android.app.Application
import com.example.newsnow.data.manager.LocalUserManagerImplementation
import com.example.newsnow.data.repository.NewsRepositoryImpl
import com.example.newsnow.domain.UseCases.app_entry.AppEntryUseCases
import com.example.newsnow.domain.UseCases.app_entry.ReadAppEntry
import com.example.newsnow.domain.UseCases.app_entry.SaveAppEntry
import com.example.newsnow.domain.UseCases.news.GetNews
import com.example.newsnow.domain.UseCases.news.NewsUseCases
import com.example.newsnow.domain.manager.LocalUserManager
import com.example.newsnow.domain.repository.NewsRepository
import com.example.newsnow.remote.NewsApi
import com.example.newsnow.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImplementation(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsApi: NewsApi
    ) : NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun providesNewsUseCases(
        newsRepository: NewsRepository
    ) : NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }

}