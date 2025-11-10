package edu.iesam.simpsonsapp.features.simpsons.data.remote

import com.google.gson.annotations.SerializedName

data class SimpsonsResponse(
    @SerializedName("results") val results: List<SimpsonsApiModel>
)