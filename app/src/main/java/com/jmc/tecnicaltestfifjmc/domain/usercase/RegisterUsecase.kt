package com.jmc.tecnicaltestfifjmc.domain.usercase

import com.jmc.tecnicaltestfifjmc.domain.model.PersonModel
import com.jmc.tecnicaltestfifjmc.domain.repository.LocalRepository
import com.jmc.tecnicaltestfifjmc.utils.courrutinas.ResultUseCase
import kotlinx.coroutines.Dispatchers


class RegisterUsecase (private val localRepository: LocalRepository) :
    ResultUseCase<PersonModel, Boolean>(
        backgroundContext = Dispatchers.IO,
        foregroundContext = Dispatchers.Main
    ) {
    override suspend fun executeOnBackground(params: PersonModel): Boolean? {

        return localRepository.saveInBdLocal(params)
    }
}