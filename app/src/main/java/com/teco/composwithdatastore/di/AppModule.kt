package com.teco.composwithdatastore.di

import android.content.Context
import com.teco.composwithdatastore.DataSharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun getDataStorePref(@ApplicationContext context: Context) = DataSharedPref(context)
}