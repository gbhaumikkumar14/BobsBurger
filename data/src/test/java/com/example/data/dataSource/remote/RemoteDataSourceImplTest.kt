package com.example.data.dataSource.remote

import com.example.data.mappers.CharacterToDomainCharacterMapper
import com.example.data.testHelper.CoroutineRule
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class RemoteDataSourceImplTest {
    private val mockService: APIService = mockk()
    private val mapper = CharacterToDomainCharacterMapper()
    private lateinit var remoteDataSource: RemoteDataSource

    @get:Rule
    val mainCoroutineRule = CoroutineRule()

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSourceImpl(apiService = mockService, mapper = mapper)
    }

    @After
    fun tearDown() {

    }
}