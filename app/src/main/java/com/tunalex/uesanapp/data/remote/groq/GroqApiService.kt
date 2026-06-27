package com.tunalex.uesanapp.data.remote.groq

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GroqApiService {
    @POST("openai/v1/chat/completions")
    suspend fun chat(
        @Header("Authorization") token: String,
        @Body request: GroqRequest
    ): GroqResponse
}
