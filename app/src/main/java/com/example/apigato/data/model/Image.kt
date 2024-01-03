package com.example.apigato.data.model


import com.squareup.moshi.Json

data class Image(
    @Json(name = "height")
    val height: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "width")
    val width: Int?
)