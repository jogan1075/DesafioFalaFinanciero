package com.jmc.tecnicaltestfifjmc.presentation.feature.login


import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.jmc.tecnicaltestfifjmc.R
import com.jmc.tecnicaltestfifjmc.domain.model.PersonModel
import com.jmc.tecnicaltestfifjmc.presentation.feature.home.MainActivity
import com.jmc.tecnicaltestfifjmc.presentation.feature.register.RegisterActivity
import com.jmc.tecnicaltestfifjmc.utils.*
import com.jmc.tecnicaltestfifjmc.utils.courrutinas.Result
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.txtInputMailLogin

import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()



        txtforgetPass.setOnClickListener { viewModel.onForgetPassClicked() }
        floatingActionButton.setOnClickListener { viewModel.onRegisterClicked() }

        btnIngresarLogin.setOnClickListener { onLogin() }

        with(viewModel) {
            observe(model, ::updateUi)
            observe(login, ::loginObserver)
        }


    }

    private fun onLogin() {
        val correo = txtInputMailLogin.editText?.text.toString()
        val pass = txtInputPassLogin.editText?.text.toString()

        if (validateMail(correo) && pass.isNotEmpty()) {
            viewModel.onLoginUser(correo, pass)
        }

    }


    fun validateMail(correo: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(correo).matches()

    }


    private fun updateUi(model: Result<String>?) {
        when (model) {
            is Result.OnSuccess -> {
                when (model.value) {
                    DESTINE_FORGET_REGISTER -> startActivity<RegisterActivity> { finish() }
//                    DESTINE_FORGET_PASS -> startActivity<ForgetPassword> { finish() }
                }
            }

        }
    }


    private fun loginObserver(result: Result<PersonModel>?) {
        when (result) {
            is Result.OnLoading -> {
//                pBarRegister.visibility = View.VISIBLE
            }
            is Result.OnSuccess -> {
//                pBarRegister.visibility = View.GONE
//                dialog("Registro", "El registro de usuario fue exitoso")
                val correo = txtInputMailLogin.editText?.text.toString()
                startActivity<MainActivity> {
                    putExtra("user", result.value.nameModel)
                }

            }
            is Result.OnError -> {
//                pBarRegister.visibility = View.GONE

//                dialog(
//                    "Registro",
//                    "Ocurrio un problema con el  registro de usuario, intente nuevamente"
//                )
            }
            else -> {
//                pBarRegister.visibility = View.GONE
            }
        }
    }
}