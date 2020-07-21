package com.jmc.tecnicaltestfifjmc.presentation.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jmc.tecnicaltestfifjmc.R
import com.jmc.tecnicaltestfifjmc.domain.model.CurrenciesModel
import com.jmc.tecnicaltestfifjmc.domain.model.ItemCurrenciesModel
import com.jmc.tecnicaltestfifjmc.utils.BaseAdapter
import com.jmc.tecnicaltestfifjmc.utils.BaseViewHolder
import com.jmc.tecnicaltestfifjmc.utils.onClickOnce

class CurrencyAdapter(private val manager: CurrencyManager) : BaseAdapter<ItemCurrenciesModel>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemCurrenciesModel> {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)

        return CurrencyViewHolder(itemView).also {
            with(itemView) {
                onClickOnce {
                    val item = it.resolveItem()

                    if (item != null)
                        manager.onCurrencyClicked(item, it.adapterPosition)
                }
            }
        }
    }

    override fun provideComparator() = compareBy(ItemCurrenciesModel::code)

}