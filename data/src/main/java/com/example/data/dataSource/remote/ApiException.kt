package com.example.data.dataSource.remote

class ApiException(val errorCode: Int, val errorBody: String?): Exception()