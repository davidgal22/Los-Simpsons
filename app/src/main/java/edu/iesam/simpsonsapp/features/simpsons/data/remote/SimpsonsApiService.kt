package edu.iesam.simpsonsapp.features.simpsons.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpsonsApiService {

    @GET("characters")
    suspend fun findAll(): Response<SimpsonsResponse>

    @GET("characters/{id}")
    suspend fun findById(@Path("id") id: String): Response<SimpsonsApiModel>

}