package com.app.integracioncomunitariaapp.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Postulation(
    @SerializedName("idPostulation")
    val idPostulation: Long,
    @SerializedName("idPetition")
    val idPetition: Long,
    @SerializedName("idUser")
    val idUser: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: Date,
    @SerializedName("idState")
    val idState: Long
)
