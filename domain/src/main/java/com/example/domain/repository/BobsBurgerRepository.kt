package com.example.domain.repository

import com.example.common.response.Result
import com.example.domain.model.DomainCharacter
import kotlinx.coroutines.CoroutineDispatcher

interface BobsBurgerRepository {
    suspend fun getAllCharacters(): Result<List<DomainCharacter>>

    suspend fun getCharacterDetail(id: Int): Result<DomainCharacter>
}