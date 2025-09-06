package com.app.integracioncomunitariaapp.repository

import com.app.integracioncomunitariaapp.model.Postulation
import com.app.integracioncomunitariaapp.network.ApiService

class PostulationRepository(private val apiService: ApiService) {

    suspend fun createPostulation(postulation: Postulation): Postulation {
        return apiService.createPostulation(postulation)
    }

    suspend fun getPostulationsByPetition(idPetition: Long): List<Postulation> {
        return apiService.getPostulationsByPetition(idPetition)
    }
}
