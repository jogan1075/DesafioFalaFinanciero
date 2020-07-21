package com.jmc.tecnicaltestfifjmc.domain.usercase


import com.jmc.tecnicaltestfifjmc.domain.model.CurrenciesModel
import com.jmc.tecnicaltestfifjmc.domain.repository.RemoteRepository
import com.jmc.tecnicaltestfifjmc.utils.courrutinas.ResultUseCase
import kotlinx.coroutines.Dispatchers

class HomeUsercase(private val remoteRepository: RemoteRepository) :
    ResultUseCase<Unit, CurrenciesModel>(
        backgroundContext = Dispatchers.IO,
        foregroundContext = Dispatchers.Main
    ) {
    override suspend fun executeOnBackground(params: Unit): CurrenciesModel? {
        return remoteRepository.getListCurrencies()
    }


}