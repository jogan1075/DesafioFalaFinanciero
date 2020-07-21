package com.jmc.tecnicaltestfifjmc.presentation.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jmc.tecnicaltestfifjmc.R
import com.jmc.tecnicaltestfifjmc.domain.model.ItemCurrenciesModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val item = intent.getSerializableExtra("itemCurrency") as? ItemCurrenciesModel


        if (item != null) {
            tvName.text =item.name
            tvValue.text =item.value
            tvCode.text =item.code
            tvCurrency.text =item.currency
            tvDate.text =item.date
        }
        }

}