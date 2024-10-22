package com.example.domain.usecase

import com.example.common.response.Result
import com.example.domain.model.DomainCharacter

interface GetCharacterDetailUseCase {
    suspend fun getCharacterDetail(id: Int): Result<DomainCharacter>
}