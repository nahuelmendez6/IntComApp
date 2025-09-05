package com.app.integracioncomunitariaapp.model

import com.google.gson.annotations.SerializedName

data class Postulation(
    @SerializedName("id") val id: Long,
    @SerializedName("idPetition") val idPetition: Long,
    @SerializedName("idProvider") val idProvider: Long,
    @SerializedName("winner") val winner: String?,
    @SerializedName("idState") val idState: Long,
    @SerializedName("current") val current: String?
)