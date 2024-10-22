package com.example.domain.usecase

import com.example.common.response.Result
import com.example.domain.model.DomainCharacter
import kotlinx.coroutines.CoroutineDispatcher

interface GetAllCharacterUseCase {
    suspend fun getAllCharacters(): Result<List<DomainCharacter>>
}