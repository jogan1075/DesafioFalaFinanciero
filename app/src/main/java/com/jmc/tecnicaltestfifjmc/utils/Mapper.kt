package com.jmc.tecnicaltestfifjmc.utils

import com.jmc.tecnicaltestfifjmc.data.entity.api.Currencies
import com.jmc.tecnicaltestfifjmc.data.entity.api.CurrencyItem
import com.jmc.tecnicaltestfifjmc.data.source.local.entity.PersonEntity
import com.jmc.tecnicaltestfifjmc.domain.model.CurrenciesModel
import com.jmc.tecnicaltestfifjmc.domain.model.ItemCurrenciesModel
import com.jmc.tecnicaltestfifjmc.domain.model.PersonModel


fun PersonEntity.toPerson() = PersonModel(
    nameModel = nombre,
    apellidoModel = apellido,
    correoModel = correo,
    passwordModel = password

)


fun Currencies.fromRemoteToDomain(): CurrenciesModel {

    val currencies = arrayListOf<ItemCurrenciesModel>()
    uf?.fromRemoteToDomain()?.let { currencies.add(it) }
    ivp?.fromRemoteToDomain()?.let { currencies.add(it) }
    dollar?.fromRemoteToDomain()?.let { currencies.add(it) }
    exchangedDollar?.fromRemoteToDomain()?.let { currencies.add(it) }
    euro?.fromRemoteToDomain()?.let { currencies.add(it) }
    ipc?.fromRemoteToDomain()?.let { currencies.add(it) }
    utm?.fromRemoteToDomain()?.let { currencies.add(it) }
    imacec?.fromRemoteToDomain()?.let { currencies.add(it) }
    tpm?.fromRemoteToDomain()?.let { currencies.add(it) }
    copperPound?.fromRemoteToDomain()?.let { currencies.add(it) }
    unEmploymentRate?.fromRemoteToDomain()?.let { currencies.add(it) }
    bitcoin?.fromRemoteToDomain()?.let { currencies.add(it) }

    return CurrenciesModel(
        version = version ?: "",
        author = author ?: "",
        date = date?.formatDate() ?: "",
        currencies = currencies
    )
}

fun CurrencyItem.fromRemoteToDomain() = ItemCurrenciesModel(
    code = code ?: "",
    name = name ?: "",
    currency = currency ?: "",
    date = date?.formatDate() ?: "",
    value = "${value?.applyMilesSeparator()} ${getSuffix(currency)}"
)

fun getSuffix(currency: String?) = when (currency) {
    PERCENTAGE -> PERCENTAGE_SUFFIX
    DOLLAR -> DOLLAR_SUFFIX
    PESOS -> PESOS_SUFIX
    else -> ""
}

const val PERCENTAGE = "Porcentaje"
const val PERCENTAGE_SUFFIX = "%"
const val DOLLAR = "Dólar"
const val DOLLAR_SUFFIX = "USD"
const val PESOS = "Pesos"
const val PESOS_SUFIX = "CLP"