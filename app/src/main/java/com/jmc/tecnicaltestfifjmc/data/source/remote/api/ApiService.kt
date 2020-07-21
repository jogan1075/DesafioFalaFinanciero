package com.jmc.tecnicaltestfifjmc.data.source.remote.api

import com.jmc.tecnicaltestfifjmc.data.entity.api.Currencies
import com.jmc.tecnicaltestfifjmc.utils.API_VERSION
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(API_VERSION)
    fun getCurrenciesListAsync(): Call<Currencies>

}