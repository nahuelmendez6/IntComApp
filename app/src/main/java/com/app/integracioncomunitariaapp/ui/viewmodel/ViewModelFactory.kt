package com.app.integracioncomunitariaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.integracioncomunitariaapp.repository.PetitionRepository
import com.app.integracioncomunitariaapp.repository.PostulationRepository
import com.app.integracioncomunitariaapp.ui.petition.PetitionViewModel
import com.app.integracioncomunitariaapp.ui.postulation.PostulationViewModel

class ViewModelFactory(private val petitionRepository: PetitionRepository, private val postulationRepository: PostulationRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetitionViewModel::class.java)) {
            return PetitionViewModel(petitionRepository) as T
        }
        if (modelClass.isAssignableFrom(PostulationViewModel::class.java)) {
            return PostulationViewModel(postulationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
