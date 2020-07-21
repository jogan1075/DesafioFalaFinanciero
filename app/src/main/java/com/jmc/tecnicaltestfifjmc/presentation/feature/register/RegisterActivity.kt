package com.jmc.tecnicaltestfifjmc.presentation.feature.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.jmc.tecnicaltestfifjmc.R

import com.jmc.tecnicaltestfifjmc.presentation.feature.login.LoginActivity
import com.jmc.tecnicaltestfifjmc.utils.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.imgBtnClose
import kotlinx.android.synthetic.main.activity_register.txtInputCorreo
import com.jmc.tecnicaltestfifjmc.utils.courrutinas.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModel()

    private val validations by lazy {
        arrayOf(
            txtInputMailLogin set R.string.error_empty `when` { text().isBlank() },
            txtInputApellido set R.string.error_empty `when` { text().isEmpty() },
            txtInputCorreo set R.string.error_empty `when` { text().isEmpty() },
            txtInputPass set R.string.error_empty `when` { text().isEmpty() },
            txtInputRepitPass set R.string.error_empty `when` { text().isEmpty() }
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()


        imgBtnClose.setOnClickListener { viewModel.onclosePressedClicked() }

        btnCreateAccount.setOnClickListener { validateInputs() }

        validations.setUp()

        with(viewModel) {
            observe(model, ::updateUi)
            observe(resultRegister, ::registerObserver)
        }

//        viewModel.model.observe(this, Observer(::updateUi))
//        viewModel.model.observe(this, Observer(::registerObserver))
    }

    private fun updateUi(result: Result<String>?) {
        when (result) {

            is Result.OnSuccess -> {

                when (result.value) {

                    DESTINE_BTN_BACK -> startActivity<LoginActivity> { finish() }
                    DESTINE_BTN_CLOSE -> startActivity<LoginActivity> { finish() }


                }
            }
        }
    }


    fun validateInputs() {

        validations.firstInvalid {
            requestFocus()

            selectAll()

        }.isNull {
//            iViewLogo.performClick()

            viewModel.register(
                name = txtInputMailLogin.text(),
                apellido = txtInputApellido.text(),
                correo = txtInputCorreo.text(),
                pass = txtInputPass.text()
            )
        }

//        if (txtInputName.editText!!.text.isEmpty()) {
//            txtInputName.error = "campo vacio"
//        }
//        if (txtInputApellido.editText!!.text.isEmpty()) {
//            txtInputApellido.error = "campo vacio"
//        }
//        if (txtInputCorreo.editText!!.text.isEmpty()) {
//            txtInputCorreo.error = "campo vacio"
//        }
//        if (txtInputPass.editText!!.text.isEmpty()) {
//            txtInputPass.error = "campo vacio"
//        }
//        if (txtInputRepitPass.editText!!.text.isEmpty()) {
//            txtInputRepitPass.error = "campo vacio"
//
//        }
//        if (!txtInputPass.editText!!.text
//                .equals(txtInputRepitPass.editText!!.text)
//        ) {
//            txtInputRepitPass.error = "passwords no coinciden"
//        }
    }


    private fun registerObserver(result: Result<Boolean>?) {
        when (result) {
            is Result.OnLoading -> {
                pBarRegister.visibility = View.VISIBLE
            }
            is Result.OnSuccess -> {
                pBarRegister.visibility = View.GONE
                dialog("Registro", "El registro de usuario fue exitoso")
            }
            is Result.OnError -> {
                pBarRegister.visibility = View.GONE

                dialog(
                    "Registro",
                    "Ocurrio un problema con el  registro de usuario, intente nuevamente"
                )
            }
            else -> {
                pBarRegister.visibility = View.GONE
            }
        }
    }


    private fun dialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            viewModel.onbackPressedClicked()
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.onbackPressedClicked()
    }
}