package com.epaymark.epay.ui.fragment


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.ReceiptAdapter
import com.epaymark.epay.data.model.ReceiptModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentReceptDialogBinding
import com.epaymark.epay.ui.base.BaseCenterSheetFragment
import com.epaymark.epay.utils.*
import com.epaymark.epay.utils.helpers.ScreenshotHelper
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ReceptDialogFragment : BaseCenterSheetFragment() {
    lateinit var binding: FragmentReceptDialogBinding
    private val viewModel: MyViewModel by activityViewModels()
    var recycleViewReceiptList = ArrayList<ReceiptModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recept_dialog, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        onViewClick()
    }

    private fun onViewClick() {

        binding.apply {
            imgBack.back()
            imgHome.backToHome()
            fabShare.setOnClickListener{
                shareImage()
            }
          }
        }

    private fun shareImage() {
        activity?.let {
            binding.apply {
                val screenshotHelper = ScreenshotHelper(binding.root.context)

// Get the root view of the activity or the view you want to capture
                val rootView = it.window.decorView.rootView

// Take a screenshot
                val screenshotFile = screenshotHelper.takeScreenshot(rootView)

// Check if the screenshot file is not null
                screenshotFile?.let {
                    // Share the screenshot
                    screenshotHelper.shareScreenshot(it)
                }


                /* var screenshotBitmap =cardView2.takeScreenshot()
                 val screenshotFile = File(cardView2.context.getExternalFilesDir(null), "screenshot.png")
                 saveScreenshot(screenshotBitmap, screenshotFile)*/
                //val bitmap = Bitmap.createBitmap(cardView2.width, cardView2.height, Bitmap.Config.ARGB_8888)

                //val canvas = Canvas(bitmap)

                //cardView2.draw(canvas)
                //shareBitmapWithoutSaving(bitmap,context)
                // shareBitmap(bitmap,cardView2.context)
            }
        }


    }

    private fun shareBitmapWithoutSaving(bitmap: Bitmap, context: Context?) {
        try {
            // Create a temporary file to store the bitmap
            val cachePath = File(context?.applicationContext?.cacheDir, "images")
            cachePath.mkdirs()
            val file = File(cachePath, "shared_image.png")
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()

            // Create an Intent to share the file URI
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))

            // Start the activity for sharing
            startActivity(Intent.createChooser(shareIntent, "Share Image"))
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle the exception (e.g., show an error message)
        }
    }

    fun shareBitmap(bitmap: Bitmap, context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        val uri: Uri = getImageUriFromBitmap(bitmap,context)
        Log.d("TAG_bitmap", "shareBitmap: ${uri.toFile()} ")
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        //startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    private fun getImageUriFromBitmap(bitmap: Bitmap, context: Context): Uri {
        val cachePath = File(context.applicationContext.cacheDir, "images")
        cachePath.mkdirs() // don't forget to make the directory
        val stream = FileOutputStream("$cachePath/image.png") // overwrites this image every time
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()
        return FileProvider.getUriForFile(context, "com.epaymark.epay.fileprovider", cachePath)
    }


    fun initView() {

        binding.recycleViewReceiptDetails.apply {
            recycleViewReceiptList.add(ReceiptModel("Transaction Id","300000025", type = 1))
            recycleViewReceiptList.add(ReceiptModel("Subscriber/ Customer Number","8583863153", type = 1))
            recycleViewReceiptList.add(ReceiptModel("Transaction Amount","₹10.00", type = 1))
            recycleViewReceiptList.add(ReceiptModel("Running Balance","₹200.22", type = 1))
            recycleViewReceiptList.add(ReceiptModel("Operator","AIRTEL", type = 1))
            recycleViewReceiptList.add(ReceiptModel("Operator ID","N/A", type = 1))
            adapter= ReceiptAdapter(recycleViewReceiptList)
        }
    }

    fun setObserver() {
        binding.apply {

        }

    }

    @Throws(WriterException::class)
    fun encodeAsBitmap(str: String): Bitmap? {
        val writer = QRCodeWriter()
        val bitMatrix: BitMatrix = writer.encode(str, BarcodeFormat.QR_CODE, 400, 400)
        val w: Int = bitMatrix.getWidth()
        val h: Int = bitMatrix.getHeight()
        val pixels = IntArray(w * h)
        for (y in 0 until h) {
            for (x in 0 until w) {
                pixels[y * w + x] = if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h)
        return bitmap
    }
}