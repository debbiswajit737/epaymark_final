package com.epaymark.epay.utils.helpers

import android.app.DatePickerDialog
import android.util.Base64
import android.view.View
import android.widget.TextView
import com.epaymark.epay.utils.helpers.Constants.AES_CODE
import com.epaymark.epay.utils.helpers.Constants.CHIPER_CODE
import com.epaymark.epay.utils.helpers.Constants.SECRET_KEY
import com.epaymark.epay.utils.`interface`.CallBack
import java.security.SecureRandom
import java.util.Calendar
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object helper {


    fun String.encryptData(encryptionKey: String): String {
        try {
            // Generate a random salt
            val salt = ByteArray(16)
            val secureRandom = SecureRandom()
            secureRandom.nextBytes(salt)

            // Derive the key from the password
            val factory = SecretKeyFactory.getInstance(AES_CODE)
            val keySpec = PBEKeySpec(encryptionKey.toCharArray(), salt, 65536, 256)
            val tmp = factory.generateSecret(keySpec)
            val secretKey = SecretKeySpec(tmp.encoded, SECRET_KEY)

            // Initialize the cipher
            val cipher = Cipher.getInstance(CHIPER_CODE)
            val iv = ByteArray(16)
            secureRandom.nextBytes(iv)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(iv))

            // Encrypt the data
            val encryptedData = cipher.doFinal(this.toByteArray())

            // Combine salt, IV, and encrypted data
            val result = ByteArray(salt.size + iv.size + encryptedData.size)
            System.arraycopy(salt, 0, result, 0, salt.size)
            System.arraycopy(iv, 0, result, salt.size, iv.size)
            System.arraycopy(encryptedData, 0, result, salt.size + iv.size, encryptedData.size)

            return Base64.encodeToString(result, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun String.decryptData( encryptionKey: String): String {
        try {
            val encryptedBytes = Base64.decode(this, Base64.DEFAULT)

            // Extract salt, IV, and encrypted data
            val salt = ByteArray(16)
            val iv = ByteArray(16)
            val encryptedContent = ByteArray(encryptedBytes.size - 32)
            System.arraycopy(encryptedBytes, 0, salt, 0, salt.size)
            System.arraycopy(encryptedBytes, salt.size, iv, 0, iv.size)
            System.arraycopy(encryptedBytes, salt.size + iv.size, encryptedContent, 0, encryptedContent.size)

            // Derive the key from the password
            val factory = SecretKeyFactory.getInstance(AES_CODE)
            val keySpec = PBEKeySpec(encryptionKey.toCharArray(), salt, 65536, 256)
            val tmp = factory.generateSecret(keySpec)
            val secretKey = SecretKeySpec(tmp.encoded, SECRET_KEY)

            // Initialize the cipher for decryption
            val cipher = Cipher.getInstance(CHIPER_CODE)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))

            // Decrypt the data
            val decryptedData = cipher.doFinal(encryptedContent)

            return String(decryptedData)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }



}