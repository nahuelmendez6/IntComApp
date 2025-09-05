package com.app.integracioncomunitariaapp.network

import com.app.integracioncomunitariaapp.model.Petition
import com.app.integracioncomunitariaapp.model.Postulation
import retrofit2.http.GET
import retrofit2.http.Path

interface PostulationApiService {

    @GET("api/postulations/by-petition/{idPetition}")
    suspend fun getPostulationsByPetition(@Path("idPetition") idPetition: Long): List<Postulation>

    @GET("api/postulations/by-provider/{idProvider}")
    suspend fun getPostulationsByProvider(@Path("idProvider") idProvider: Long): List<Postulation>

    @GET("api/petitions")
    suspend fun getPetitions(): List<Petition>
}
