package com.example.data.dataSource.remote

import com.example.common.response.Result
import com.example.domain.model.DomainCharacter
import kotlinx.coroutines.CoroutineDispatcher

interface RemoteDataSource {
    suspend fun getAllCharacters(): Result<List<DomainCharacter>>
    suspend fun getCharacterDetail(id: Int): Result<DomainCharacter>
}