package edu.iesam.simpsonsapp.features.simpsons.data.remote

import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons
import edu.iesam.simpsonsapp.features.simpsons.domain.SimpsonsRepository

class SimpsonsDataSource(private val remoteDataSource: SimpsonsApiRemoteDataSource) :
    SimpsonsRepository {
    override suspend fun findAll(): Result<List<Simpsons>> {
        return remoteDataSource.getSimpsons()
    }
}