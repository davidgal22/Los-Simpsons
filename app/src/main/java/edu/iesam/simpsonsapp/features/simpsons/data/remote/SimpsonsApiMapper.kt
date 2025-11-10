package edu.iesam.simpsonsapp.features.simpsons.data.remote

import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons

fun SimpsonsApiModel.toModel(): Simpsons {
    val baseUrl = "https://cdn.thesimpsonsapi.com/500"
    return Simpsons(
        id = id,
        age = age,
        name = name,
        occupation = occupation,
        urlImage = baseUrl + portrait_path
    )
}