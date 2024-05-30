package com.example.organizafinancas.di

import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.CategoryRepositoryImpl
import com.example.organizafinancas.data.repository.PaymentRepository
import com.example.organizafinancas.data.repository.PaymentRepositoryImpl
import com.example.organizafinancas.data.repository.PaymentTypeRepository
import com.example.organizafinancas.data.repository.PaymentTypeRepositoryImpl
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
    abstract fun bindPaymentRepository(repository: PaymentRepositoryImpl): PaymentRepository

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository

    @Singleton
    @Binds
    abstract fun bindPaymentTypeRepository(repository: PaymentTypeRepositoryImpl): PaymentTypeRepository
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