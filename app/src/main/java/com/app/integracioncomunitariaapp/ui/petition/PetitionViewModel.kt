package com.app.integracioncomunitariaapp.ui.petition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.integracioncomunitariaapp.model.Petition
import com.app.integracioncomunitariaapp.repository.PetitionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetitionViewModel(private val repository: PetitionRepository) : ViewModel() {

    private val _petitions = MutableStateFlow<List<Petition>>(emptyList())
    val petitions: StateFlow<List<Petition>> = _petitions

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchPetitions() {
        viewModelScope.launch {
            try {
                _petitions.value = repository.getPetitions()
            } catch (e: Exception) {
                _error.value = "Error fetching petitions: ${e.message}"
            }
        }
    }
}
