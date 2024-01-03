package com.example.apigato.data.votos


import com.squareup.moshi.Json

data class Boton_enviar(
    @field:Json(name = "image_id")
    val imageId: String?,
    @field:Json(name = "sub_id")
    val subId: String?,
    @field:Json(name = "value")
    val value: Int?
)