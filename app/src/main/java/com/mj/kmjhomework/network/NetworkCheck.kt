package com.mj.kmjhomework.network

import com.mj.kmjhomework.data.model.ErrorResponse
import okhttp3.ResponseBody
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException
import retrofit2.Retrofit

object NetworkCheck : KoinComponent {
    private val retrofit: Retrofit by inject()

    fun getErrorResponse(errorBody: ResponseBody): ErrorResponse {
        return retrofit.responseBodyConverter<ErrorResponse>(
            ErrorResponse::class.java,
            ErrorResponse::class.java.annotations
        ).convert(errorBody) ?: ErrorResponse("")
    }

    fun getErrorMessage(e: Throwable): String {
        return (if (e is HttpException) {
            val errorBody = e.response()?.errorBody()
            if (errorBody != null) {
                "[ErrorBody] ${e.code()} , ${e.message()} , ${getErrorResponse(errorBody)}"
            } else {
                "[HttpExceptionError] ${e.code()} , ${e.message()}"
            }
        } else {
            e.toString()
        })
    }
}