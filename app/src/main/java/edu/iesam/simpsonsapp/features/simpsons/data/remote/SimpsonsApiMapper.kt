package edu.iesam.simpsonsapp.features.simpsons.data.remote

import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons

fun SimpsonsApiModel.toModel(): Simpsons {
    return Simpsons(
        this.id,
        this.age,
        this.name,
        this.occupation,
        this.portrait_path
    )
}