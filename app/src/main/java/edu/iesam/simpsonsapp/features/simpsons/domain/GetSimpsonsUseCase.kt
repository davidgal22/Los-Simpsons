package edu.iesam.simpsonsapp.features.simpsons.domain

class GetSimpsonsUseCase (val repository: SimpsonsRepository){
    suspend operator fun invoke(): Result<List<Simpsons>>{
      return repository.findAll()
    }

}