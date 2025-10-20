package edu.iesam.simpsonsapp.features.simpsons.domain

interface SimpsonsRepository {
    //El suspend es para que se ejecute en hilo secundario por si tarde en responder la api
    //Result es para que me de la lista o error
    suspend fun findAll(): Result<List<Simpsons>>


}