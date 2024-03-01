package com.example.summary10.di

import com.example.summary10.data.remote.repository.AccountRepositoryImpl
import com.example.summary10.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}