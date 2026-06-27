package com.tunalex.uesanapp.data.remote.groq

import com.tunalex.uesanapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val GROQ_API_KEY: String get() = BuildConfig.GROQ_API_KEY

object GroqRetrofitInstance {
    val api: GroqApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.groq.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GroqApiService::class.java)
    }
}
