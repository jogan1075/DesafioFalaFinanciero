package com.jmc.tecnicaltestfifjmc.presentation.feature.register

import androidx.lifecycle.ViewModel
import com.jmc.tecnicaltestfifjmc.domain.model.PersonModel
import com.jmc.tecnicaltestfifjmc.domain.usercase.RegisterUsecase
import com.jmc.tecnicaltestfifjmc.utils.*

//import com.jmc.tecnicaltestfifjmc.utils.Encrypt.secretKey

class RegisterViewModel(
    private val registerUsecase: RegisterUsecase
) :
    ViewModel() {

    val resultRegister = LiveResult<Boolean>()


    val model = LiveResult<String>()


    fun onbackPressedClicked() {
        model.postSuccess(DESTINE_BTN_BACK)

    }

    fun onclosePressedClicked() {
        model.postSuccess(DESTINE_BTN_CLOSE)
    }

    fun register(name: String, apellido: String, correo: String, pass: String) {

        val encriptPass: String? = Encrypt().encrypt(pass, Encrypt().secretKey)
        var personModel = PersonModel( name, apellido, correo, encriptPass!!)

        registerUsecase.execute(
            liveData = resultRegister,
            params = personModel
        )
    }

}