package com.example.data.dataSource.remote

import com.example.common.response.Result
import com.example.data.mappers.CharacterToDomainCharacterMapper
import com.example.domain.model.DomainCharacter
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor (
    private val apiService: APIService,
    private val mapper: CharacterToDomainCharacterMapper,
) : BaseRemoteDataSource(), RemoteDataSource {

    override suspend fun getAllCharacters(): Result<List<DomainCharacter>> {
        return callAPI({ apiService.getAllCharacters() }, mapper::mapCharacterList)
    }

    override suspend fun getCharacterDetail(id: Int): Result<DomainCharacter> {
        return callAPI({apiService.getCharacterDetail(id = id)}, mapper::mapCharacter)
    }

}