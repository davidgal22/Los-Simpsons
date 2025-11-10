package edu.iesam.simpsonsapp.features.simpsons.domain

interface SimpsonsRepository {
    suspend fun findAll(): Result<List<Simpsons>>

    suspend fun findById(id: String): Result<Simpsons>

}