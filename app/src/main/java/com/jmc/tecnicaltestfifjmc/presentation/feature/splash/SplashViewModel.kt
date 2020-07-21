package com.jmc.tecnicaltestfifjmc.presentation.feature.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmc.tecnicaltestfifjmc.utils.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val TIME_SLEEP: Long = 2000

class SplashViewModel : ViewModel() {

//    sealed class UiModel {
//        object Navigation : UiModel()
//    }

//    private val _model = MutableLiveData<UiModel>()
    val model= LiveResult<String>()

//        get() {
//            if (_model.value == null) onContinue()
//            return _model
//        }

    fun onContinue() {
        GlobalScope.launch {
            delay(TIME_SLEEP)
            model.postSuccess(DESTINE_LOGIN)
        }
    }

}