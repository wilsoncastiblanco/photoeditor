package com.example.photoeditor

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ed3a0a52c3336015dfdf29936d15ec066eea4d6945f48e9009766a269a5612c0"
            )
            .build()

        chain.proceed(newUrl)
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(authInterceptor)
        .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun photosService(): PhotosApi {
        return retrofit().create(PhotosApi::class.java)
    }
}