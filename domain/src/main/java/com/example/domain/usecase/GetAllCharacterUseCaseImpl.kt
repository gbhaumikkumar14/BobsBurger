package com.example.domain.usecase

import com.example.common.response.Result
import com.example.domain.model.DomainCharacter
import com.example.domain.repository.BobsBurgerRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllCharacterUseCaseImpl @Inject constructor(
    private val repository: BobsBurgerRepository
) : GetAllCharacterUseCase {
    override suspend fun getAllCharacters(): Result<List<DomainCharacter>> {
        return repository.getAllCharacters()
    }

}