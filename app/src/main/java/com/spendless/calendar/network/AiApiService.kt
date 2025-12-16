package com.spendless.calendar.network

import retrofit2.http.Body
import retrofit2.http.POST

// 요청 데이터
data class CategoryRequest(
    val memo: String
)

// 응답 데이터
data class CategoryResponse(
    val category: String,
    val confidence: Float
)

interface AiApiService {

    @POST("/classify")
    suspend fun classifyCategory(
        @Body request: CategoryRequest
    ): CategoryResponse
}
