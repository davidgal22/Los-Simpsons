package edu.iesam.simpsonsapp.features.simpsons.domain

//Sealed es para sellar clase y que solo tengo dos atributos
sealed class ErrorApp: Throwable() {
    //object es para no crear mas instancias de los atributos
    object NetworkError : ErrorApp()
    object ServerError : ErrorApp()
}