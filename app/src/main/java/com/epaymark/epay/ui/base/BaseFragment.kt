package com.epaymark.epay.ui.base

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import com.epaymark.epay.utils.helpers.SharedPreff
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException
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

    fun String.videoToBase64(context: Context): String? {
        val contentResolver: ContentResolver = context.contentResolver

        try {
            // Open an input stream to the content URI
            val inputStream: InputStream? = contentResolver.openInputStream(Uri.parse(this))

            if (inputStream != null) {
                // Read the video data into a byte array
                val buffer = ByteArray(1024)
                var bytesRead: Int
                val output = ByteArrayOutputStream()

                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                }

                // Convert the byte array to Base64
                val videoData = output.toByteArray()
                val base64Video = Base64.encodeToString(videoData, Base64.DEFAULT)

                inputStream.close()
                output.close()
                Log.d("kol", "videoToBase64: ")
                return base64Video
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("kol", "videoToBase64: "+e.message)
        }catch (e: Exception) {
            e.printStackTrace()
            Log.d("kol", "videoToBase642: "+e.message)
        }

        return null
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




