package com.example.myapplication.request

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class BaseRepository {

    fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Flow<T?> = flow {
        call.invoke().let {
            if (it.isSuccessful) {
                emit(it.body())
            } else {
                throw Throwable("Error: ${it.message()}")
            }
        }
    }
}