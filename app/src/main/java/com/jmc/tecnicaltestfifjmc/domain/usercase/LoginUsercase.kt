package com.jmc.tecnicaltestfifjmc.domain.usercase

import com.jmc.tecnicaltestfifjmc.domain.model.PersonModel
import com.jmc.tecnicaltestfifjmc.domain.repository.LocalRepository
import com.jmc.tecnicaltestfifjmc.utils.courrutinas.ResultUseCase
import kotlinx.coroutines.Dispatchers

class LoginUsercase(private val localRepository: LocalRepository) :
    ResultUseCase<PersonModel, PersonModel>(
        backgroundContext = Dispatchers.IO,
        foregroundContext = Dispatchers.Main
    ) {
    override suspend fun executeOnBackground(params: PersonModel): PersonModel? {
        return localRepository.getUserBdLocalByMailandPass(
            correo = params.correoModel,
            pass = params.passwordModel
        )
    }


}