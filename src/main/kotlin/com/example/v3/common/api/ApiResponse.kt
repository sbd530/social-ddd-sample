package com.example.v3.common.api

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
) {
    companion object {
        private const val SUCCESS_CODE = 0
        private const val FAILURE_CODE = 1
        private const val SUCCESS_MESSAGE = "success"

        fun <T> success(data: T) =
            ApiResponse(
                code = SUCCESS_CODE,
                message = SUCCESS_MESSAGE,
                data = data
            )

        fun failure(code: Int, message: String) =
            ApiResponse(
                code = code,
                message = message,
                data = null
            )
    }
}


