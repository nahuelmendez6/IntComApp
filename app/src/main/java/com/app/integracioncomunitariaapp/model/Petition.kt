package com.app.integracioncomunitariaapp.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Petition(
    @SerializedName("idPetition")
    val idPetition: Long,
    @SerializedName("idTypePetition")
    val idTypePetition: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("dateSince")
    val dateSince: Date,
    @SerializedName("dateUntil")
    val dateUntil: Date,
    @SerializedName("idUserCreate")
    val idUserCreate: Long,
    @SerializedName("idUserUpdate")
    val idUserUpdate: Long,
    @SerializedName("idCustomer")
    val idCustomer: Long,
    @SerializedName("idState")
    val idState: Long
)
