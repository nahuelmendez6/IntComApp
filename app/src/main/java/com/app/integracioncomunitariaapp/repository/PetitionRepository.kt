package com.app.integracioncomunitariaapp.repository

import com.app.integracioncomunitariaapp.model.Petition
import com.app.integracioncomunitariaapp.network.ApiService

class PetitionRepository(private val apiService: ApiService) {

    suspend fun getPetitions(): List<Petition> {
        return apiService.getPetitions()
    }
}