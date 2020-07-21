package com.jmc.tecnicaltestfifjmc.data.source.local

import com.jmc.tecnicaltestfifjmc.data.source.local.database.FifDatabase
import com.jmc.tecnicaltestfifjmc.data.source.local.entity.PersonEntity
import com.jmc.tecnicaltestfifjmc.domain.model.PersonModel
import com.jmc.tecnicaltestfifjmc.domain.repository.LocalRepository
import com.jmc.tecnicaltestfifjmc.utils.Encrypt
import com.jmc.tecnicaltestfifjmc.utils.toPerson

class LocalDataSource(private val fifDatabase: FifDatabase) : LocalRepository {


    override suspend fun saveInBdLocal(persona: PersonModel): Boolean {

        val personEntity = PersonEntity(
            0,
            persona.nameModel,
            persona.apellidoModel,
            persona.correoModel,
            persona.passwordModel
        )
        return fifDatabase.getPersonDao().insertPersona(personEntity) > 0
    }

    override suspend fun getUserBdLocalByMailandPass(
        correo: String,
        pass: String
    ): PersonModel {

        val result = fifDatabase.getPersonDao().getPersonaByMailAndPass(correo, pass)

        return PersonModel(result.nombre, result.apellido, result.correo, result.password)

    }

}