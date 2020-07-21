package com.jmc.tecnicaltestfifjmc.domain.repository

import com.jmc.tecnicaltestfifjmc.domain.model.CurrenciesModel

interface RemoteRepository {

    suspend fun getListCurrencies(): CurrenciesModel
}