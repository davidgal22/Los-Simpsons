package edu.iesam.simpsonsapp.features.simpsons.data.remote

import edu.iesam.simpsonsapp.core.api.ApiClient
import edu.iesam.simpsonsapp.core.error.ErrorApp
import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimpsonsApiRemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getSimpsons(): Result<List<Simpsons>> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = apiClient.createService(SimpsonsApiService::class.java)
                val resultSimpsons = apiService.findAll()

                if (resultSimpsons.isSuccessful && resultSimpsons.body() != null) {
                    val responseBody = resultSimpsons.body()!!
                    Result.success(responseBody.results.map { it.toModel() })
                } else {
                    Result.failure(ErrorApp.ServerError)
                }
            } catch (e: Exception) {
                Result.failure(ErrorApp.ServerError)
            }
        }
    }

    suspend fun getSimpsonById(id: String): Result<Simpsons> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = apiClient.createService(SimpsonsApiService::class.java)
                val resultSimpson = apiService.findById(id)

                if (resultSimpson.isSuccessful && resultSimpson.body() != null) {
                    Result.success(resultSimpson.body()!!.toModel())
                } else {
                    Result.failure(ErrorApp.ServerError)
                }
            } catch (e: Exception) {
                Result.failure(ErrorApp.ServerError)
            }
        }
    }
}