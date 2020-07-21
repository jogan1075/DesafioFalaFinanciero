package com.jmc.tecnicaltestfifjmc

import com.jmc.tecnicaltestfifjmc.utils.Encrypt
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val secretKey: String =
            "662ede816988e58fb6d057d9d85605e0"

//        var encryptor: Encrypt = Encrypt()

        val encryptedValue: String? =Encrypt().encrypt("test", secretKey)
        println(encryptedValue)

        val decryptedValue: String? =Encrypt().decryptWithAES(secretKey, encryptedValue)
        println(decryptedValue)
    }
}