package com.example.domain.di

import com.example.domain.repository.BobsBurgerRepository
import com.example.domain.usecase.GetAllCharacterUseCase
import com.example.domain.usecase.GetAllCharacterUseCaseImpl
import com.example.domain.usecase.GetCharacterDetailUseCase
import com.example.domain.usecase.GetCharacterDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetAllCharactersUseCase(
        getAllCharacterUseCaseImpl: GetAllCharacterUseCaseImpl
    ): GetAllCharacterUseCase

    @Binds
    abstract fun bindGetCharacterDetailUseCase(
        getCharacterDetailsUseCaseImpl: GetCharacterDetailsUseCaseImpl
    ): GetCharacterDetailUseCase
}