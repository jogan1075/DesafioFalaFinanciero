package com.jmc.tecnicaltestfifjmc.utils

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.CycleInterpolator
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.jmc.tecnicaltestfifjmc.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import com.jmc.tecnicaltestfifjmc.utils.courrutinas.Result
import kotlinx.coroutines.*
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/* Live data */

typealias LiveResult<T> = MutableLiveData<Result<T>>

/* LiveResult */

@JvmName("postCompleteResult")
fun <T> LiveResult<T>.postSuccess(value: T) = postValue(Result.OnSuccess(value))

@JvmName("postThrowableResult")
fun <T> LiveResult<T>.postThrowable(throwable: Throwable) = postValue(Result.OnError(throwable))

@JvmName("postLoadingResult")
fun <T> LiveResult<T>.postLoading() = postValue(Result.OnLoading())

@JvmName("postCancelResult")
fun <T> LiveResult<T>.postCancel() = postValue(Result.OnCancel())

@JvmName("postEmptyResult")
fun <T> LiveResult<T>.postEmpty() = postValue(Result.OnEmpty())


/* Coroutines */

suspend fun <T> Call<T>.await() = suspendCoroutine<T?> { continuation ->
    enqueue(object : Callback<T?> {
        override fun onResponse(call: Call<T?>, response: Response<T?>) {
            if (response.isSuccessful)
                continuation.resume(response.body())
            else
                continuation.resumeWithException(HttpException(response))
        }

        override fun onFailure(call: Call<T?>, t: Throwable) {
            continuation.resumeWithException(t)
        }
    })
}


@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {
    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }
    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}


val Context.app: App
    get() = applicationContext as App


inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent {
    return Intent(this, T::class.java).apply(body)
}

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

inline fun <reified T : Activity> Context.startActivityWithParams(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}

inline fun Any?.isNull(exec: () -> Unit) = this ?: exec()

inline fun <T> T?.notNull(exec: (T) -> Unit): T? = this?.also { exec(this) }

fun TextInputLayout.selectAll() = editText?.selectAll()

infix fun TextInputLayout.set(@StringRes resource: Int) = this to resource

fun TextInputLayout.text(value: String? = null) = value?.also { editText?.setText(it) }
    ?: "${editText?.text}"

infix fun Pair<TextInputLayout, Int>.`when`(valid: TextInputLayout.() -> Boolean) =
    Validations(first, second, valid)


fun Array<Validations>.firstInvalid(onFound: (TextInputLayout.() -> Unit)? = null) =
    firstOrNull { set ->
        set.run {
            validator(view).also { invalid ->
                if (invalid) {
                    with(view) {
                        error = context.getString(set.resource)
                        onFound.notNull { it.invoke(this) }
                    }
                }
            }
        }
    }


fun EditText.onTextChanged(event: (CharSequence, Int, Int, Int) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            event(s ?: "", start, before, count)
        }

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    })
}


fun <T, L : LiveData<T>> FragmentActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))


/* AlertDialog */

var (AlertDialog.Builder).titleResource: Int
    @Deprecated(
        message = NO_GETTER,
        level = DeprecationLevel.ERROR
    ) get() = throw NotImplementedError()
    set(@StringRes value) {
        setTitle(value)
    }

var (AlertDialog.Builder).messageResource: Int
    @Deprecated(
        message = NO_GETTER,
        level = DeprecationLevel.ERROR
    ) get() = throw NotImplementedError()
    set(@StringRes value) {
        setMessage(value)
    }


fun (AlertDialog.Builder).positiveButton(
    @StringRes buttonTextResource: Int,
    onClicked: () -> Unit = {}
) {
    setPositiveButton(buttonTextResource) { _, _ -> onClicked() }
}

fun Context.alert(builder: AlertDialog.Builder.() -> Unit) =
    AlertDialog.Builder(this).apply(builder)


private const val YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val MMM_DD_YYYY_FORMAT = "dd MMM, yyyy"

fun String.formatDate(): String {
    var formattedDate = ""
    try {
        var format = SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS_SSS_Z, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        var newDate: Date? = null
        try {
            newDate = format.parse(this)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        format = SimpleDateFormat(MMM_DD_YYYY_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getDefault()

        formattedDate = format.format(newDate)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return formattedDate
}


fun Double.applyMilesSeparator(): String {
    val df = DecimalFormat("#,###.##")
    df.toLocalizedPattern()
    df.roundingMode = RoundingMode.CEILING
    return df.format(this)
}


/* Click - Likstener*/
fun View.onClickOnce(onClick: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        override fun onClick(view: View) {
            view.setOnClickListener(null)

            also { listener ->
                CoroutineScope(Dispatchers.Main).launch {
                    onClick()

                    withContext(Dispatchers.IO) { delay(500) }

                    view.setOnClickListener(listener)
                }
            }
        }
    })
}


fun ConnectivityManager.isNetworkAvailable(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getNetworkCapabilities(activeNetwork)?.run {
            when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
    } else {
        @Suppress("DEPRECATION")
        activeNetworkInfo?.run {
            when (type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                else -> false
            }
        }
    } ?: false
}