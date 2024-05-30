package com.example.organizafinancas.di

import com.example.organizafinancas.data.repository.Repository
import com.example.organizafinancas.data.repository.RepositoryImpl
import com.example.organizafinancas.data.source.local.CategoryDao
import com.example.organizafinancas.data.source.local.CategoryDaoImpl
import com.example.organizafinancas.data.source.local.PaymentDao
import com.example.organizafinancas.data.source.local.PaymentDaoImpl
import com.example.organizafinancas.data.source.local.PaymentTypeDao
import com.example.organizafinancas.data.source.local.PaymentTypeDaoImpl
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

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Singleton
    @Binds
    abstract fun bindPaymentDao(dao: PaymentDaoImpl): PaymentDao

    @Singleton
    @Binds
    abstract fun bindCategoryDao(dao: CategoryDaoImpl): CategoryDao

    @Singleton
    @Binds
    abstract fun bindPaymentTypeDao(dao: PaymentTypeDaoImpl): PaymentTypeDao
}