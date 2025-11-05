package edu.iesam.simpsonsapp.features.simpsons.data.remote

import edu.iesam.simpsonsapp.core.api.ApiClient

import edu.iesam.simpsonsapp.features.simpsons.domain.ErrorApp
import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimpsonsApiRemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getSimpsons(): Result<List<Simpsons>> {
        return withContext(Dispatchers.IO) {
            val apiService = apiClient.createService(SimpsonsApiService::class.java)
            val resultSimpsons = apiService.findAll()

            if (resultSimpsons.isSuccessful && resultSimpsons.body() != null) {
                val listSimpsonsApiModel = resultSimpsons.body()!!
                val listSimpsons = listSimpsonsApiModel.map { it.toModel() }
                Result.success(listSimpsons)
            } else {
                Result.failure(ErrorApp.ServerError)
            }
        }
    }
}