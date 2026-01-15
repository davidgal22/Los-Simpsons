package edu.iesam.simpsonsapp.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val Base_url = "https://thesimpsonsapi.com/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(Base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createService(typeClass: Class<T>): T {
        return retrofit.create(typeClass)
    }
}

