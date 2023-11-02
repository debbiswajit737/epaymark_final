package com.epaymark.epay.ui.base

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.epaymark.epay.utils.helpers.SharedPreff
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment: Fragment(){
    @Inject
    lateinit var sharedPreff: SharedPreff



    fun Uri.uriToBase64(contentResolver: ContentResolver): String? {
        var inputStream: InputStream? = null
        try {
            inputStream = contentResolver.openInputStream(this)
            if (inputStream != null) {
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                val byteArray = byteArrayOutputStream.toByteArray()
                return Base64.encodeToString(byteArray, Base64.DEFAULT)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun String.videoToBase64(): String? {
        try {
            val file = File(this)
            val length = file.length().toInt()
            val bytes = ByteArray(length)

            val input = FileInputStream(file)
            input.read(bytes)
            input.close()

            val base64String = Base64.encodeToString(bytes, Base64.DEFAULT)
            return base64String
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun String.pdfToBase64(): String? {
        try {
            val file = File(this)
            val length = file.length().toInt()
            val bytes = ByteArray(length)

            val input = FileInputStream(file)
            input.read(bytes)
            input.close()

            val base64String = Base64.encodeToString(bytes, Base64.DEFAULT)
            return base64String
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }





}