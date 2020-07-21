package com.jmc.tecnicaltestfifjmc.data.entity.api

import com.squareup.moshi.Json

data class Currencies(

    @field:Json(name = "autor")
    val author: String? = null,

    @field:Json(name = "version")
    val version: String? = null,

    @field:Json(name = "fecha")
    val date: String? = null,

    @field:Json(name =  "ivp")
    val ivp: CurrencyItem? = null,

    @field:Json(name = "euro")
    val euro: CurrencyItem? = null,

    @field:Json(name = "dolar_intercambio")
    val exchangedDollar: CurrencyItem? = null,

    @field:Json(name = "dolar")
    val dollar: CurrencyItem? = null,

    @field:Json(name = "libra_cobre")
    val copperPound: CurrencyItem? = null,

    @field:Json(name = "utm")
    val utm: CurrencyItem? = null,

    @field:Json(name = "tpm")
    val tpm: CurrencyItem? = null,

    @field:Json(name = "uf")
    val uf: CurrencyItem? = null,

    @field:Json(name = "tasa_desempleo")
    val unEmploymentRate: CurrencyItem? = null,

    @field:Json(name = "ipc")
    val ipc: CurrencyItem? = null,

    @field:Json(name = "imacec")
    val imacec: CurrencyItem? = null,

    @field:Json(name = "bitcoin")
    val bitcoin: CurrencyItem? = null
)
