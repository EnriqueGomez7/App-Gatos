package com.example.apigato.data.votos


import com.squareup.moshi.Json

data class Peticion(
    @Json(name = "country_code")
    val countryCode: String?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: Image?,
    @Json(name = "image_id")
    val imageId: String?,
    @Json(name = "sub_id")
    val subId: String?,
    @Json(name = "value")
    val value: Int?
)