package com.example.data.dataSource.remote

import com.example.common.response.Result
import com.example.data.constants.Constants
import retrofit2.Response
import java.io.IOException

abstract class BaseRemoteDataSource() {
    protected suspend fun <T, R> callAPI(
        response: suspend () -> Response<T>,
        mapper: (T) -> R
    ): Result<R> {
        return try {
            val result = response()
            if (result.isSuccessful) {
                Result.Success(mapper(result.body()!!))
            } else {
                Result.Failure(ApiException(result.code(), result.errorBody().toString()))
            }
        } catch (exception: Exception) {
            when (exception) {
                is IOException -> Result.Failure(exception)
                else -> Result.Failure(Exception(Constants.NETWORK_ERROR))
            }
        }
    }
}