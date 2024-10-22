package com.example.data.repository

import com.example.common.response.Result
import com.example.data.dataSource.remote.RemoteDataSource
import com.example.domain.model.DomainCharacter
import com.example.domain.repository.BobsBurgerRepository
import javax.inject.Inject

class BobsBurgerRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : BobsBurgerRepository {

    override suspend fun getAllCharacters(): Result<List<DomainCharacter>> {
        return dataSource.getAllCharacters()
    }

    override suspend fun getCharacterDetail(id: Int): Result<DomainCharacter> {
        return dataSource.getCharacterDetail(id)
    }

}