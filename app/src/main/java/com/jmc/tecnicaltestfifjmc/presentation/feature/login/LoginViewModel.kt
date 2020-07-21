package com.jmc.tecnicaltestfifjmc.presentation.feature.login

import androidx.lifecycle.ViewModel
import com.jmc.tecnicaltestfifjmc.domain.model.PersonModel
import com.jmc.tecnicaltestfifjmc.domain.usercase.LoginUsercase
import com.jmc.tecnicaltestfifjmc.domain.usercase.RegisterUsecase
import com.jmc.tecnicaltestfifjmc.utils.*
import kotlinx.coroutines.CoroutineDispatcher


class LoginViewModel(private val loginUsercase: LoginUsercase) : ViewModel() {

    val login = LiveResult<PersonModel>()
    val model = LiveResult<String>()


    fun onRegisterClicked() {
        model.postSuccess(DESTINE_FORGET_REGISTER)
    }

    fun onForgetPassClicked() {
        model.postSuccess(DESTINE_FORGET_PASS)
    }

    fun onLoginUser(correo: String, pass: String) {

        var personModel =
            PersonModel("", "apellido", correo, Encrypt().encrypt(pass, Encrypt().secretKey)!!)

        loginUsercase.execute(liveData = login, params = personModel)

    }
}