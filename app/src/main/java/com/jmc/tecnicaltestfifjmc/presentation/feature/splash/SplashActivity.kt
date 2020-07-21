package com.jmc.tecnicaltestfifjmc.presentation.feature.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.jmc.tecnicaltestfifjmc.R
import com.jmc.tecnicaltestfifjmc.presentation.feature.login.LoginActivity
import com.jmc.tecnicaltestfifjmc.utils.LiveResult
import com.jmc.tecnicaltestfifjmc.utils.courrutinas.Result
import com.jmc.tecnicaltestfifjmc.utils.observe
import com.jmc.tecnicaltestfifjmc.utils.startActivity


import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        with(viewModel) {
            onContinue()
            observe(model, ::initUi)
        }
    }

    private fun initUi(model: Result<String>?) {
        when (model) {
            is Result.OnSuccess -> {
                startActivity<LoginActivity> {}
                finish()
            }
        }
    }


}