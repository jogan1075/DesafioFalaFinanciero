package com.jmc.tecnicaltestfifjmc.domain.model

import java.io.Serializable

data class ItemCurrenciesModel(
    val code: String,
    val name: String,
    val currency: String,
    val date: String,
    val value: String
) : Serializable