package com.example.data.di

import com.example.data.repository.BobsBurgerRepositoryImpl
import com.example.domain.repository.BobsBurgerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBobsBurgerRepository(
        bobsBurgerRepositoryImpl: BobsBurgerRepositoryImpl): BobsBurgerRepository
}