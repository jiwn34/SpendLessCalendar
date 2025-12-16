package com.spendless.calendar.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // ⚠️ 에뮬레이터 기준 localhost
    // 실제 기기면 PC IP로 바꿔야 함
    private const val BASE_URL = "http://10.0.2.2:8000/"

    val api: AiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AiApiService::class.java)
    }
}
