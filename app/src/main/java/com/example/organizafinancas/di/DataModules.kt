package com.example.organizafinancas.di

import android.content.Context
import androidx.room.Room
import com.example.organizafinancas.data.repository.CategoryRepository
import com.example.organizafinancas.data.repository.CategoryRepositoryImpl
import com.example.organizafinancas.data.repository.PaymentRepository
import com.example.organizafinancas.data.repository.PaymentRepositoryImpl
import com.example.organizafinancas.data.repository.PaymentTypeRepository
import com.example.organizafinancas.data.repository.PaymentTypeRepositoryImpl
import com.example.organizafinancas.data.source.local.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
object DatabaseModule {

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase) = appDatabase.categoryDao()

    @Provides
    fun providePaymentTypeDao(appDatabase: AppDatabase)= appDatabase.paymentTypeDao()

    @Provides
    fun providePaymentDao(appDatabase: AppDatabase) = appDatabase.paymentDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "OrganizaFinancas.db"
        )
            .fallbackToDestructiveMigration()
            .build()
}
