package com.example.apigato.data.votos


import com.squareup.moshi.Json

data class Votes(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "message")
    val message: String?
)