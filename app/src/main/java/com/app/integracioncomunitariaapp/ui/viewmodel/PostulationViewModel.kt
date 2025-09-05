package com.app.integracioncomunitariaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.integracioncomunitariaapp.model.Postulation
import com.app.integracioncomunitariaapp.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostulationViewModel : ViewModel() {

    private val _postulations = MutableStateFlow<List<Postulation>>(emptyList())
    val postulations: StateFlow<List<Postulation>> = _postulations

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getPostulationsByPetition(idPetition: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _postulations.value = RetrofitClient.instance.getPostulationsByPetition(idPetition)
            } catch (e: Exception) {
                _error.value = "Error fetching postulations: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getPostulationsByProvider(idProvider: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _postulations.value = RetrofitClient.instance.getPostulationsByProvider(idProvider)
            } catch (e: Exception) {
                _error.value = "Error fetching postulations: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
