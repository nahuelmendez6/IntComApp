package com.app.integracioncomunitariaapp.repository

import com.app.integracioncomunitariaapp.model.Petition
import com.app.integracioncomunitariaapp.network.RetrofitClient

class PetitionRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getPetitions(): List<Petition> {
        return apiService.getPetitions()
    }
}
