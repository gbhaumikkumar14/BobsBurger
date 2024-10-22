package com.example.domain.usecase

import com.example.common.response.Result
import com.example.domain.model.DomainCharacter
import com.example.domain.repository.BobsBurgerRepository
import javax.inject.Inject

class GetCharacterDetailsUseCaseImpl @Inject constructor(
    private val bobsBurgerRepository: BobsBurgerRepository
): GetCharacterDetailUseCase {

    override suspend fun getCharacterDetail(id: Int): Result<DomainCharacter> {
        return bobsBurgerRepository.getCharacterDetail(id)
    }
}