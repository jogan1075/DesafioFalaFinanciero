package com.jmc.tecnicaltestfifjmc.domain.model

data class CurrenciesModel(
    val version: String,
    val author: String,
    val date: String,
    val currencies: List<ItemCurrenciesModel>
){
    companion object {
        fun create() = CurrenciesModel(
            version = "",
            author = "",
            date = "",
            currencies = arrayListOf()
        )
    }
}