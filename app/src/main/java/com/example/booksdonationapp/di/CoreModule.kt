package com.example.booksdonationapp.di

import android.app.Application
import android.content.Context
import com.example.booksdonationapp.core.IoDispatcher
import com.example.booksdonationapp.presentation.commun.sharedPrefs.AppSHaredPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @ApplicationContext
    @Provides
    fun provideAppContext(app: Application): Context = app.applicationContext

    @Provides
    fun provideAppSharedPrefs( app: Application): AppSHaredPref =
        AppSHaredPref(app)

}