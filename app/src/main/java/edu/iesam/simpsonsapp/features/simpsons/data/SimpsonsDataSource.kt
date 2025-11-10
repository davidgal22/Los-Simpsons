package edu.iesam.simpsonsapp.features.simpsons.data

import edu.iesam.simpsonsapp.features.simpsons.data.remote.SimpsonsApiRemoteDataSource
import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons
import edu.iesam.simpsonsapp.features.simpsons.domain.SimpsonsRepository

class SimpsonsDataSource(private val remoteDataSource: SimpsonsApiRemoteDataSource) :
    SimpsonsRepository {
    override suspend fun findAll(): Result<List<Simpsons>> {
        return remoteDataSource.getSimpsons()
    }

    override suspend fun findById(id: String): Result<Simpsons> {
        return remoteDataSource.getSimpsonById(id)
    }
}