package com.jmc.tecnicaltestfifjmc.utils

import android.util.Patterns
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

fun Array<Validations>.setUp() {
    forEach { set ->
        with(set.view) {
            editText.notNull { editText ->
                editText.onTextChanged { _, _, _, _ ->
                    error = null
                }
            }
        }
    }
}

class Validations(val view: TextInputLayout,
                 @StringRes val resource: Int,
                 val validator: (TextInputLayout.() -> Boolean))

