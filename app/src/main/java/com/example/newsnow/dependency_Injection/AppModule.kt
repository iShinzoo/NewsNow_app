package com.example.newsnow.dependency_Injection

import android.app.Application
import com.example.newsnow.data.manager.LocalUserManagerImplementation
import com.example.newsnow.domain.UseCases.AppEntryUseCases
import com.example.newsnow.domain.UseCases.ReadAppEntry
import com.example.newsnow.domain.UseCases.SaveAppEntry
import com.example.newsnow.domain.manager.LocalUserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}