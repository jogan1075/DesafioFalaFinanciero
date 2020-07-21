package com.jmc.tecnicaltestfifjmc.data.source.remote

import com.jmc.tecnicaltestfifjmc.data.source.remote.api.ApiService
import com.jmc.tecnicaltestfifjmc.domain.model.CurrenciesModel
import com.jmc.tecnicaltestfifjmc.domain.repository.RemoteRepository
import com.jmc.tecnicaltestfifjmc.utils.fromRemoteToDomain
import com.jmc.tecnicaltestfifjmc.utils.await

class RemoteDataSource(private val service: ApiService ) : RemoteRepository {


    override suspend fun getListCurrencies(): CurrenciesModel {

        return  service.getCurrenciesListAsync().await()!!.fromRemoteToDomain()
    }
}
