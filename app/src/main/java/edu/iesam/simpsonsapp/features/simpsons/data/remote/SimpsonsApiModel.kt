package edu.iesam.simpsonsapp.features.simpsons.data.remote

data class SimpsonsApiModel(
    val id: String,
    val age: Int,
    val name: String,
    val occupation: String,
    val portrait_path: String
)
