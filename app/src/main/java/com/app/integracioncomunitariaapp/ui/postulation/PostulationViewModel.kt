package com.app.integracioncomunitariaapp.ui.postulation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.integracioncomunitariaapp.model.Petition
import com.app.integracioncomunitariaapp.model.Postulation
import com.app.integracioncomunitariaapp.repository.PostulationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class PostulationViewModel(private val repository: PostulationRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<PostulationUiState>(PostulationUiState.Idle)
    val uiState: StateFlow<PostulationUiState> = _uiState

    fun createPostulation(petitionId: Long, proposal: String, cost: Double) {
        viewModelScope.launch {
            _uiState.value = PostulationUiState.Loading
            try {
                // TODO: Get the real user ID
                val petition = Petition(
                    idPetition = petitionId,
                    idTypePetition = 0,
                    description = "",
                    dateSince = Date(),
                    dateUntil = Date(),
                    idUserCreate = 0,
                    idUserUpdate = 0,
                    idCustomer = 0,
                    idState = 0
                )
                val postulation = Postulation(
                    idPostulation = null,
                    petition = petition,
                    idUser = 1, // Hardcoded user ID
                    proposal = proposal,
                    date = Date(),
                    idState = 1, // Hardcoded state ID
                    cost = cost,
                    winner = 0
                )
                val createdPostulation = repository.createPostulation(postulation)
                _uiState.value = PostulationUiState.Success(createdPostulation)
            } catch (e: Exception) {
                _uiState.value = PostulationUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class PostulationUiState {
    object Idle : PostulationUiState()
    object Loading : PostulationUiState()
    data class Success(val postulation: Postulation) : PostulationUiState()
    data class Error(val message: String) : PostulationUiState()
}