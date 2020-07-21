package com.jmc.tecnicaltestfifjmc.presentation.feature.home

import com.jmc.tecnicaltestfifjmc.data.entity.api.CurrencyItem
import com.jmc.tecnicaltestfifjmc.domain.model.ItemCurrenciesModel

interface CurrencyManager {

    fun onCurrencyClicked(item: ItemCurrenciesModel, position: Int)
}