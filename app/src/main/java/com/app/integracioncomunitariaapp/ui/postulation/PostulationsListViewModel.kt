package com.app.integracioncomunitariaapp.ui.postulation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.integracioncomunitariaapp.model.Postulation
import com.app.integracioncomunitariaapp.repository.PostulationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostulationsListViewModel(private val repository: PostulationRepository) : ViewModel() {

    private val _postulations = MutableStateFlow<List<Postulation>>(emptyList())
    val postulations: StateFlow<List<Postulation>> = _postulations

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchPostulationsByPetition(petitionId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _postulations.value = repository.getPostulationsByPetition(petitionId)
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}