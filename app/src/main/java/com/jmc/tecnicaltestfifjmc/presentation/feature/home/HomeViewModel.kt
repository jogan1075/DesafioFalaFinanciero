package com.jmc.tecnicaltestfifjmc.presentation.feature.home

import androidx.lifecycle.ViewModel
import com.jmc.tecnicaltestfifjmc.domain.model.CurrenciesModel
import com.jmc.tecnicaltestfifjmc.domain.usercase.HomeUsercase
import com.jmc.tecnicaltestfifjmc.domain.usercase.LoginUsercase
import com.jmc.tecnicaltestfifjmc.utils.LiveResult

class HomeViewModel(private val homeUsercase: HomeUsercase) : ViewModel() {


    val lista = LiveResult<CurrenciesModel>()

    fun getCurrencys() {
        homeUsercase.execute(liveData = lista,params = Unit)
    }
}