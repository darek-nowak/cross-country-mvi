package com.darek.crosscountry.di

import com.darek.crosscountry.utils.CoroutineDispatchersProvider
import com.darek.crosscountry.utils.CoroutineDispatchersProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindCoroutineDispatchers(
        coroutineDispatchersProviderImpl: CoroutineDispatchersProviderImpl
    ): CoroutineDispatchersProvider
}