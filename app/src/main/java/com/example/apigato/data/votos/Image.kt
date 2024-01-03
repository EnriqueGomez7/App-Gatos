package com.example.apigato.data.votos


import com.squareup.moshi.Json

data class Image(
    @Json(name = "id")
    val id: String?,
    @Json(name = "url")
    val url: String?
)