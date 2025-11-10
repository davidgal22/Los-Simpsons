package edu.iesam.simpsonsapp.features.simpsons.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.simpsonsapp.features.simpsons.domain.GetSimpsonsUseCase
import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons
import kotlinx.coroutines.launch

class SimpsonsViewModel(
    private val getSimpsonsUseCase: GetSimpsonsUseCase
) : ViewModel() {

    private val _simpsonsList = MutableLiveData<List<Simpsons>>()
    val simpsonsList: LiveData<List<Simpsons>> get() = _simpsonsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun loadSimpsons() {
        viewModelScope.launch {
            val result = getSimpsonsUseCase()
            result
                .onSuccess { list ->
                    _simpsonsList.value = list
                }
                .onFailure {
                    _errorMessage.value = "Error loading Simpsons"
                }
        }
    }
}