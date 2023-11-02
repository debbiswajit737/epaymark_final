package com.epaymark.epay.ui.fragment.regandkyc
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.Surface
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.epaymark.epay.R
import com.epaymark.epay.databinding.FragmentCameraBinding
import com.epaymark.epay.databinding.FragmentVideoRecordingBinding
import java.io.IOException

class VideoRecordingFragment : Fragment() {
    private lateinit var surfaceView: SurfaceView
    private var camera: Camera? = null
    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    lateinit var binding: FragmentVideoRecordingBinding
    private val VIDEO_CAPTURE = 101


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_recording, container, false)
        return binding.root
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initView()
        //setObserver()
       // onViewClick()



        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, VIDEO_CAPTURE)
    }

    @SuppressLint("MissingPermission")
    private fun initView() {

        surfaceView = binding.surfaceView
        startButton = binding.startButton
        stopButton = binding.stopButton


    }





}