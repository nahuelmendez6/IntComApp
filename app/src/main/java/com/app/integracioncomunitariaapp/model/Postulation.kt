package com.app.integracioncomunitariaapp.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Postulation(
    @SerializedName("idPostulation")
    val idPostulation: Long?,
    @SerializedName("petition")
    val petition: Petition,
    @SerializedName("idUser")
    val idUser: Long,
    @SerializedName("proposal")
    val proposal: String,
    @SerializedName("date")
    val date: Date,
    @SerializedName("idState")
    val idState: Long,
    @SerializedName("cost")
    val cost: Double,
    @SerializedName("winner")
    val winner: Int
)
