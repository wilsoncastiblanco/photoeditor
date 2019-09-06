package com.example.photoeditor

import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("photos/random")
    suspend fun fetchPhotos(@Query("count") count: Int = 30): List<Photo>
}