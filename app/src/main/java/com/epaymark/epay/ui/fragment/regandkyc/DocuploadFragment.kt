package com.epaymark.epay.ui.fragment.regandkyc


import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.AuthViewModel
import com.epaymark.epay.databinding.FragmentDocuploadBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.fragment.CameraDialog
import com.epaymark.epay.utils.common.MethodClass
import com.epaymark.epay.utils.helpers.Constants.isGallary
import com.epaymark.epay.utils.helpers.Constants.isVideo
import com.epaymark.epay.utils.`interface`.CallBack

class DocuploadFragment : BaseFragment() {
    lateinit var binding: FragmentDocuploadBinding
    private val authViewModel: AuthViewModel by activityViewModels()
    private var loader: Dialog? = null
    private val VIDEO_CAPTURE = 101
    var textView: TextView?=null
    var type=""
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
                    authViewModel.videokyc.value=authViewModel.videoFile.value?.file
                    it.dismiss()
                    isVideo=false
                },12000)
            }
        }
    }
    private fun onViewClick() {
        activity?.let {act->
            binding.apply {
                llVideoKyc.setOnClickListener{
                    isVideo=true
                    /*val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)

                    // Set the maximum video duration in seconds (30 minutes = 30 * 60 seconds)
                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5000)

                    // Start the video capture activity
                    startActivityForResult(intent, VIDEO_CAPTURE)*/
                    findNavController().navigate(R.id.action_docuploadFragment_to_cameraFragment)
                }



                llPartnerPanId.setOnClickListener{
                    type="pan"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llCpan.setOnClickListener{
                    type="cpan"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llPaadhar.setOnClickListener{
                    type="paadhar"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
                llPartnerAadharBack.setOnClickListener{
                    type="PartnerAadharBack"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
                llGst.setOnClickListener{
                    type="llGst"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llCertificateOfIncorporation.setOnClickListener{
                    type="llCertificateOfIncorporation"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
                llBoardResolution.setOnClickListener{
                    type="llBoardResolution"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
                llTrade.setOnClickListener{
                    type="llTrade"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llUserSelfi.setOnClickListener{
                    type="llUserSelfi"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }

                llCselfi.setOnClickListener{
                    type="llCselfi"
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

                            getImage(s)
                        }

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }






                btnNext.setOnClickListener {

                    try {



                            binding.tvPancardVideoKycImage.setText(authViewModel.filePath.toString())
                            authViewModel.videoFile.value?.file=
                                authViewModel.filePath.value?.toString()?.videoToBase64(binding.root.context)
                                    .toString()

                            Log.d("TAG_videofileaa", "onViewClick: "+authViewModel.videoFile.value?.file)
                            Log.d("TAG_videofile", "onViewClick: "+authViewModel.filePath.value)



                        Toast.makeText(binding.root.context, ""+authViewModel.filePath.value, Toast.LENGTH_SHORT).show()
                    }catch (e:Exception){
                        Toast.makeText(binding.root.context, "Video file required \n"+e.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }

    private fun getImage(s:String) {
        when(s){
            "g"->{
                isVideo=false
                isGallary=true
                findNavController().navigate(R.id.action_docuploadFragment_to_cameraFragment)
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
            loader = MethodClass.custom_loader(it, getString(R.string.please_wait))
        }
    }

    fun setObserver() {
        authViewModel.filePath.observe(viewLifecycleOwner){
            when(type){
                "pan"->{
                    authViewModel.panPath.value=it.toString()
                }
                "cpan"->{
                    authViewModel.cpanPath.value=it.toString()
                }
                "paadhar"->{
                    authViewModel.paadhar.value=it.toString()
                }
                "PartnerAadharBack"->{
                    authViewModel.PartnerAadharBack.value=it.toString()
                }
                "llGst"->{
                authViewModel.llGst.value=it.toString()
                }
                "llCertificateOfIncorporation"->{
                authViewModel.llCertificateOfIncorporation.value=it.toString()
                }
                "llBoardResolution"->{
                authViewModel.llBoardResolution.value=it.toString()
                }
                "llTrade"->{
                authViewModel.llTrade.value=it.toString()
                }
                "llUserSelfi"->{
                authViewModel.llUserSelfi.value=it.toString()
                }
                "llCselfi"->{
                authViewModel.llCselfi.value=it.toString()
                }


            }

            Log.d("TAG_file", "true setObserver: "+it.uriToBase64(binding.root.context.contentResolver))
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
}