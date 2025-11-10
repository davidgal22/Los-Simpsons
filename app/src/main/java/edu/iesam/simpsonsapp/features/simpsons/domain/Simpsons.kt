package edu.iesam.simpsonsapp.features.simpsons.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
Implementa la interfaz Parcelable, que permite que los objetos de esta clase
se puedan "empaquetar" y pasar entre componentes, como de una pantalla a otra.
Actúa como un "pasaporte" para que el objeto pueda viajar dentro del sistema Android.
 */

@Parcelize
data class Simpsons(
    val id: String,
    val age: Int,
    val name: String,
    val occupation: String,
    val urlImage: String
) : Parcelable