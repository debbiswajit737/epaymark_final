package com.epaymark.epay.ui.fragment.regandkyc


import android.app.Activity
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.data.model.onBoading.DocumentUploadModel
import com.epaymark.epay.data.viewMovel.AuthViewModel
import com.epaymark.epay.databinding.FragmentDocuploadBinding
import com.epaymark.epay.ui.activity.DashboardActivity
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.fragment.CameraDialog
import com.epaymark.epay.utils.common.MethodClass
import com.epaymark.epay.utils.helpers.Constants.isAfterReg
import com.epaymark.epay.utils.helpers.Constants.isBackCamera
import com.epaymark.epay.utils.helpers.Constants.isGallary
import com.epaymark.epay.utils.helpers.Constants.isPdf
import com.epaymark.epay.utils.helpers.Constants.isVideo
import com.epaymark.epay.utils.helpers.SharedPreff
import com.epaymark.epay.utils.`interface`.CallBack
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

class DocuploadFragment : BaseFragment() {
    lateinit var binding: FragmentDocuploadBinding
    private val authViewModel: AuthViewModel by activityViewModels()
    private var loader: Dialog? = null
    private val VIDEO_CAPTURE = 101
    var textView: TextView?=null
    var type=""
    @Inject
    override lateinit var sharedPreff: SharedPreff
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_docupload, container, false)
        binding.viewModel = authViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        onViewClick()
    }

    override fun onResume() {
        super.onResume()
        if (isVideo){
            loader?.let {
                it.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    authViewModel.videokycBase64.value=authViewModel.videoFilePath.value?.getVideoPathFromContentUri(binding.root.context)?.videoToBase64()
                    authViewModel.videokyc.value=authViewModel.videoFilePath.value?.getFileNameFromUri()
                    it.dismiss()
                    //authViewModel.videokycBase64.value.toString().testDataFile()
                    //authViewModel?.videokycBase64?.value?.decodeBase64ToVideo(binding.root.context)
                    isVideo=false
                },15000)
            }
        }
    }
    private fun onViewClick() {
        activity?.let {act->
            binding.apply {
                llVideoKyc.setOnClickListener{
                    isGallary=false
                    isVideo=true
                    isBackCamera=false
                    /*val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)

                    // Set the maximum video duration in seconds (30 minutes = 30 * 60 seconds)
                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5000)

                    // Start the video capture activity
                    startActivityForResult(intent, VIDEO_CAPTURE)*/
                    findNavController().navigate(R.id.action_docuploadFragment_to_cameraFragment)
                }



                llPartnerPanId.setOnClickListener{
                    isBackCamera=true
                    type="pan"
                    isPdf=false
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llCpan.setOnClickListener{
                    isBackCamera=true
                    isPdf=false
                    type="cpan"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llPaadhar.setOnClickListener{
                    isBackCamera=true
                    isPdf=false
                    type="paadhar"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
                llPartnerAadharBack.setOnClickListener{
                    isBackCamera=true
                    isPdf=false
                    type="PartnerAadharBack"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
                llGst.setOnClickListener{
                    isBackCamera=true
                    isPdf=true
                    type="llGst"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llCertificateOfIncorporation.setOnClickListener{
                    isBackCamera=true
                    isPdf=true
                    type="llCertificateOfIncorporation"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
                llBoardResolution.setOnClickListener{
                    isBackCamera=true
                    isPdf=true
                    type="llBoardResolution"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
                llTrade.setOnClickListener{
                    isBackCamera=true
                    isPdf=true
                    type="llTrade"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llUserSelfi.setOnClickListener{
                    isBackCamera=false
                    isPdf=false
                    type="llUserSelfi"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llCselfi.setOnClickListener{
                    isBackCamera=false
                    isPdf=false
                    type="llCselfi"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                btnNext.setOnClickListener {
                    authViewModel.apply {
                    try {
                        binding.tvPancardVideoKycImage.setText(authViewModel.filePath.toString())
                        /*videoFile.value?.file =
                            filePath.value?.toString()
                                ?.videoToBase64(binding.root.context)
                                .toString()*/
                        authViewModel.videokycBase64.value=filePath.value?.getVideoPathFromContentUri(binding.root.context)?.
                            videoToBase64()
                            .toString()


                        if (docValidation()) {
                          //  sharedPreff?.setLoginData()

                            val documentUploadModel = DocumentUploadModel(
                                panPathBase64= panPathBase64.value/*?.encrypt()*/,
                                cpanPathBase64=cpanPathBase64.value/*?.encrypt()*/,
                                paadharBase64=paadharBase64.value/*?.encrypt()*/,
                                partnerAadharBackBase64=PartnerAadharBackBase64.value?.encrypt(),
                                llGstBase64=llGstBase64.value/*?.encrypt()*/,
                                llCertificateOfIncorporationBase64=llCertificateOfIncorporationBase64.value/*?.encrypt()*/,
                                llBoardResolutionBase64=llBoardResolutionBase64.value/*?.encrypt()*/,
                                llTradeBase64=llTradeBase64 . value/*?.encrypt()*/,
                                llUserSelfiBase64=llUserSelfiBase64.value/*?.encrypt()*/,
                                llCselfiBase64=llCselfiBase64 . value/*?.encrypt()*/,
                                videokycBase64=videokycBase64.value/*?.encrypt()*/,
                            )

                            //json.toString().testDataFile()
                            /*val regModel = DocumentUploadModel(
                                panPathBase64= panPathBase64.value,
                                cpanPathBase64=cpanPathBase64.value,
                                paadharBase64=paadharBase64.value,
                                partnerAadharBackBase64=PartnerAadharBackBase64.value,
                                llGstBase64=llGstBase64.value,
                                llCertificateOfIncorporationBase64=llCertificateOfIncorporationBase64.value,
                                llBoardResolutionBase64=llBoardResolutionBase64.value,
                                llTradeBase64=llTradeBase64 . value,
                                llUserSelfiBase64=llUserSelfiBase64.value,
                                llCselfiBase64=llCselfiBase64 . value,
                                videokycBase64=videokycBase64.value,
                            )*/
                            /*cpanPathBase64
                            llCselfiBase64
                            llUserSelfiBase64
                            paadharBase64
                            panPathBase64
                            partnerAadharBackBase64
                             */

                            //startActivity(Intent(requireActivity(), DashboardActivity::class.java))
                            val intent = Intent(requireActivity(), DashboardActivity::class.java)
                            intent.putExtra(isAfterReg,true)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            requireActivity().startActivity(intent)
                             //authViewModel.documentRegistration(documentUploadModel)

                            //Toast.makeText(binding.root.context, "Ok", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(binding.root.context, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
                }
            }
        }

    }


    // Function to convert a video file URI to Base64
    fun uriToBase64(context: Context, videoUri: Uri): String {
        val contentResolver: ContentResolver = context.contentResolver

        // Open an input stream from the content resolver
        val inputStream: InputStream? = contentResolver.openInputStream(videoUri)

        // Use a ByteArrayOutputStream to read the data from the input stream
        val buffer = ByteArray(8192) // You can adjust the buffer size as needed
        val outputStream = ByteArrayOutputStream()

        try {
            var bytesRead: Int
            while (inputStream!!.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
        } finally {
            inputStream?.close()
        }

        // Convert the bytes to a Base64 encoded string
        val base64String = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)

        return base64String
    }

    private fun getImage(s:String) {
        when(s){
            "g"->{
                isVideo=false
                isGallary=true
                if (!isPdf) {
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
                else{
                    findNavController().navigate(R.id.action_docuploadFragment_to_cameraFragment)
                }
                //
            }
            "t"->{
                isVideo=false
                isGallary=false
                findNavController().navigate(R.id.action_docuploadFragment_to_cameraFragment)
            }

        }
    }

    fun initView() {
        binding.root.context?.let {
            sharedPreff = SharedPreff(context)
            loader = MethodClass.custom_loader(it, getString(R.string.please_wait))
        }
    }

    fun setObserver() {

        authViewModel.apply {
            authViewModel.filePath.observe(viewLifecycleOwner){

                when(type){
                    "pan"->{
                        panPathBase64.value=it?.uriToBase64(binding.root.context.contentResolver)
                        panPath.value=it?.getFileNameFromUri()
                    }
                    "cpan"->{
                        cpanPathBase64.value=it?.uriToBase64(binding.root.context.contentResolver)
                        cpanPath.value=it?.getFileNameFromUri()
                    }
                    "paadhar"->{
                        paadharBase64.value=it?.uriToBase64(binding.root.context.contentResolver)
                        paadhar.value=it?.getFileNameFromUri()
                    }
                    "PartnerAadharBack"->{
                        PartnerAadharBackBase64.value=it?.uriToBase64(binding.root.context.contentResolver)
                        PartnerAadharBack.value=it?.getFileNameFromUri()
                    }
                    "llGst"->{
                        llGstBase64.value=it?.toString()?.pdfToBase64(binding.root.context)
                        llGst.value=it?.getFileNameFromUri()
                    }
                    "llCertificateOfIncorporation"->{
                        llCertificateOfIncorporationBase64.value=it?.toString()?.pdfToBase64(binding.root.context)
                        llCertificateOfIncorporation.value=it?.getFileNameFromUri()
                    }
                    "llBoardResolution"->{
                        llBoardResolutionBase64.value=it?.toString()?.pdfToBase64(binding.root.context)
                        llBoardResolution.value=it?.getFileNameFromUri()
                    }
                    "llTrade"->{
                        llTradeBase64.value=it?.toString()?.pdfToBase64(binding.root.context)
                        llTrade.value=it?.getFileNameFromUri()
                    }
                    "llUserSelfi"->{
                        llUserSelfiBase64.value=it?.uriToBase64(binding.root.context.contentResolver)
                        llUserSelfi.value=it?.getFileNameFromUri()
                    }
                    "llCselfi"->{
                        llCselfiBase64.value=it?.uriToBase64(binding.root.context.contentResolver)
                        llCselfi.value=it?.getFileNameFromUri()
                    }


                }

                //Log.d("TAG_file", "true setObserver: "+it.uriToBase64(binding.root.context.contentResolver))
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val videoUri = data?.data

        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(requireContext(), "Video saved to:\n"
                        + videoUri, Toast.LENGTH_LONG).show()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(requireContext(), "Video recording cancelled.",
                    Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Failed to record video",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

        if (uri != null) {
            authViewModel.filePath.value = uri

            //findNavController().navigate(R.id.action_homeFragment_to_previewFragment)
        } else {
            authViewModel.filePath.value =Uri.parse("/")

            Log.d("PhotoPicker", "No media selected")
        }

    }
}