package com.epaymark.epay.ui.base

import android.app.DatePickerDialog
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.InputFilter
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.utils.helpers.Constants.INPUT_FILTER_MAX_VALUE
import com.epaymark.epay.utils.helpers.Constants.INPUT_FILTER_POINTER_LENGTH
import com.epaymark.epay.utils.helpers.DecimalDigitsInputFilter
import com.epaymark.epay.utils.helpers.SharedPreff
import com.epaymark.epay.utils.`interface`.CallBack
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Calendar
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

    fun View.back(){
        this.setOnClickListener{
            findNavController().popBackStack()
        }

    }


    fun EditText.setupAmount() {
        /*val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            // Define your filter logic here
            val newText = dest.toString().substring(0, dstart) + source.subSequence(start, end) + dest.toString().substring(dend)
            if (newText.matches(Regex("^\\d{0,7}(\\.\\d{0,2})?\$"))) {
                null // Accept the input
            } else {
                "" // Reject the input
            }
        }*/

        //this.filters = arrayOf(inputFilter)
        this.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(INPUT_FILTER_MAX_VALUE, INPUT_FILTER_POINTER_LENGTH))

    }
    fun View.showDatePickerDialog(callBack: CallBack) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this.context,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = "$year-${month + 1}-$dayOfMonth" // +1 because months are zero-based
                callBack.getValue(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
    fun String.currentdate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = dateFormat.format(calendar.time)
        return currentDate
    }



}




