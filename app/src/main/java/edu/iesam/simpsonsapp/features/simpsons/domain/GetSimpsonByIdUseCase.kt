package edu.iesam.simpsonsapp.features.simpsons.domain

class GetSimpsonByIdUseCase(private val repository: SimpsonsRepository) {
    suspend operator fun invoke(id: String): Result<Simpsons> {
        return repository.findById(id)
    }
}