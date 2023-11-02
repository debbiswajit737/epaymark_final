package com.epaymark.epay.ui.fragment.regandkyc


import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.AuthViewModel
import com.epaymark.epay.databinding.FragmentDocuploadBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.fragment.CameraDialog
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isGallary
import com.epaymark.epay.utils.helpers.Constants.isVideo
import com.epaymark.epay.utils.`interface`.CallBack
import java.io.File

class DocuploadFragment : BaseFragment() {
    lateinit var binding: FragmentDocuploadBinding
    private val authViewModel: AuthViewModel by activityViewModels()

    private val VIDEO_CAPTURE = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_docupload, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        onViewClick()
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
                    val cameraDialog = CameraDialog(object : CallBack {
                        override fun getValue(s: String) {

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

                    })
                    cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)
                }
            }
        }

    }

    fun initView() {

    }

    fun setObserver() {
        authViewModel.filePath.observe(viewLifecycleOwner){
            Log.d("TAG_file", "true setObserver: "+it/*.uriToBase64(binding.root.context.contentResolver)*/)
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