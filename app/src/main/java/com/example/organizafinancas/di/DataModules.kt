package com.example.organizafinancas.di

import com.example.organizafinancas.data.repository.Repository
import com.example.organizafinancas.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository
}