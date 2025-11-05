package edu.iesam.simpsonsapp.features.simpsons.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SimpsonsApiService {

    @GET("characters")
    suspend fun findAll(): Response<List<SimpsonsApiModel>>

}