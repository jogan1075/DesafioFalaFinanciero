package com.jmc.tecnicaltestfifjmc.presentation.feature.home

import android.view.View
import com.jmc.tecnicaltestfifjmc.domain.model.CurrenciesModel
import com.jmc.tecnicaltestfifjmc.domain.model.ItemCurrenciesModel
import com.jmc.tecnicaltestfifjmc.utils.BaseViewHolder
import kotlinx.android.synthetic.main.item_currency.view.*

open class CurrencyViewHolder(
    itemView: View
) :
    BaseViewHolder<ItemCurrenciesModel>(itemView) {
    override fun bindView(item: ItemCurrenciesModel) {
        with(itemView) {
            tvName.text = item.name
            tvValue.text = item.value

        }
    }
}