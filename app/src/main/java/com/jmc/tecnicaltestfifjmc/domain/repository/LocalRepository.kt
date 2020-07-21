package com.jmc.tecnicaltestfifjmc.domain.repository

import com.jmc.tecnicaltestfifjmc.domain.model.PersonModel

interface LocalRepository {

    suspend fun saveInBdLocal(persona: PersonModel) : Boolean
    suspend fun getUserBdLocalByMailandPass(correo:String, pass:String) : PersonModel

}