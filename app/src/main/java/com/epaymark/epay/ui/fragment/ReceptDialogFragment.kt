package com.epaymark.epay.ui.fragment


import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.ReceiptAdapter
import com.epaymark.epay.data.model.ReceiptModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentReceptDialogBinding
import com.epaymark.epay.ui.base.BaseCenterSheetFragment
import com.epaymark.epay.utils.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter


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

          }
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