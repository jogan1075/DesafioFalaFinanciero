package com.jmc.tecnicaltestfifjmc.data.entity.api

import com.squareup.moshi.Json

data class CurrencyItem(
    @field:Json(name = "fecha")
    val date: String? = null,

    @field:Json(name = "codigo")
    val code: String? = null,

    @field:Json(name = "unidad_medida")
    val currency: String? = null,

    @field:Json(name = "valor")
    val value: Double? = null,

    @field:Json(name = "nombre")
    val name: String? = null
)