package edu.iesam.simpsonsapp.features.simpsons.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.simpsonsapp.core.error.ErrorApp
import edu.iesam.simpsonsapp.features.simpsons.domain.GetSimpsonByIdUseCase
import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons
import kotlinx.coroutines.launch

class SimpsonsDetailViewModel(private val getSimpsonByIdUseCase: GetSimpsonByIdUseCase) :
    ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val simpson: Simpsons? = null
    )

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSimpson(id: String) {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch {
            val result = getSimpsonByIdUseCase(id)
            result.onSuccess {
                _uiState.postValue(UiState(simpson = it))
            }.onFailure {
                _uiState.postValue(UiState(error = ErrorApp.ServerError))
            }
        }
    }
}