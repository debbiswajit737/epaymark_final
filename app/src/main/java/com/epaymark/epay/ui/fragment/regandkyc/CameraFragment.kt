package com.epaymark.epay.ui.fragment.regandkyc

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.AuthViewModel
import com.epaymark.epay.databinding.FragmentCameraBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.helpers.Constants.contentValues
import com.epaymark.epay.utils.helpers.Constants.isGallary
import com.epaymark.epay.utils.helpers.Constants.isVideo
import com.epaymark.epay.utils.helpers.PermissionUtils
import com.epaymark.epay.utils.helpers.PermissionUtils.createAlertDialog
import com.epaymark.epay.utils.`interface`.PermissionsCallback
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale


class CameraFragment : BaseFragment() {
    val TAG = "camera"
    @RequiresApi(Build.VERSION_CODES.P)


    private lateinit var countDownTimer: CountDownTimer
    private val initialCountDown: Long = 5000
    private val countDownInterval: Long = 1000

    lateinit var binding: FragmentCameraBinding
    private val authViewModel: AuthViewModel by activityViewModels()
    private var imageCapture: ImageCapture? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null
    private val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun init() {
        if (isGallary) {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        else{
            PermissionUtils.requestVideoRecordingPermission(requireActivity(), object :
                PermissionsCallback {
                override fun onPermissionRequest(granted: Boolean) {

                    if (granted) {
                        startCamera()
                        onViewClick()
                    } else {
                        dialogRecordingPermission()
                    }
                }
            })
        }
    }

   
    @RequiresApi(Build.VERSION_CODES.P)
    fun onViewClick() {
        binding.apply {
            btnCaptureImg.setOnClickListener {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                    takePhotoAndroidQ()
                }
                else{
                    takePhoto()
                }


            }
            btnCaptureVideo.setOnClickListener {
                btnCaptureVideo.visibility = View.GONE
                videoCapture()
            }
            binding.tvTimer.visibility = View.GONE
            if (!isVideo) {
                btnCaptureImg.visibility = View.VISIBLE
                btnCaptureVideo.visibility = View.GONE
            } else {
                binding.tvTimer.visibility = View.VISIBLE
                btnCaptureImg.visibility = View.GONE
                btnCaptureVideo.visibility = View.VISIBLE
            }

        }

    }

    private fun takePhotoAndroidQ() {
        val outputDirectory = File(binding.btnCaptureImg.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "YourAppDirectoryName")
        outputDirectory.mkdirs()

        val photoFile = File(outputDirectory, "photo.jpg")

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture?.takePicture(outputOptions, ContextCompat.getMainExecutor(binding.btnCaptureImg.context), object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                outputFileResults.savedUri?.let { authViewModel.filePath.value = it }
                findNavController().navigate(R.id.action_cameraFragment_to_docuploadFragment)
               // findNavController().popBackStack()
                // Image capture successful, you can handle success here
            }

            override fun onError(exception: ImageCaptureException) {
                // Handle error here
                Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
            }
        })
    }

    fun startCamera() {
        binding.root.context?.let { ctx ->
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder()
                    .build()
                    .also { mPreview ->
                        mPreview.setSurfaceProvider(
                            binding.preview.surfaceProvider
                        )

                    }
                imageCapture = ImageCapture.Builder().build()


                val recorder = Recorder.Builder()
                    .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
                    .build()
                videoCapture = VideoCapture.withOutput(recorder)

                val cameraSelector = if (isVideo) {
                    CameraSelector.DEFAULT_BACK_CAMERA
                } else CameraSelector.DEFAULT_FRONT_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        viewLifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture,
                        videoCapture
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "start camera erroe" + e.message)
                }
            }, ContextCompat.getMainExecutor(ctx))
        }

    }

    fun takePhoto() {
        val imageCapture = imageCapture ?: return
        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        /*val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "epay/Image")
            }
        }*/
        contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // For Android 10 and higher, use RELATIVE_PATH
                put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_MOVIES + "/epay")
                //put(MediaStore.Video.Media.RELATIVE_PATH, "epay/image")
            } else {
                // For versions prior to Android 10, manage the file operations manually
                val directoryPath = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    // For Android Nougat and higher, use getExternalStoragePublicDirectory
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
                } else {
                    // For versions prior to Nougat, use a hardcoded path
                    File(Environment.getExternalStorageDirectory(), "epay/image")
                }

                // Ensure the directory exists, and create it if necessary
                if (!directoryPath.exists()) {
                    directoryPath.mkdirs()
                }

                // Set the full file path
                val filePath = File(directoryPath, name).absolutePath
                put(MediaStore.MediaColumns.DATA, filePath)
            }
        }
        contentValues?.let {contentValues->


        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                requireActivity().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        imageCapture.takePicture(

            outputOptions,
            ContextCompat.getMainExecutor(binding.root.context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    output.savedUri?.let { authViewModel.filePath.value = it }
                    //findNavController().navigate(R.id.action_cameraFragment_to_previewFragment)
                    findNavController().popBackStack()

                }
            }
        )
    }

    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun videoCapture() {
        try {
            val videoCapture = this.videoCapture ?: return

            startCountdown()

            val curRecording = recording
            if (curRecording != null) {

                curRecording.stop()
                //recording = null
                return
            }


            val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
                .format(System.currentTimeMillis())
            contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name + ".mp4")
                put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // For Android 10 and higher, use RELATIVE_PATH
                  //  put(MediaStore.Video.Media.RELATIVE_PATH, "epay/video")
                    put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_MOVIES + "/epay")
                } else {
                    // For versions prior to Android 10, manage the file operations manually
                    val directoryPath = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        // For Android Nougat and higher, use getExternalStoragePublicDirectory
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
                    } else {
                        // For versions prior to Nougat, use a hardcoded path
                        File(Environment.getExternalStorageDirectory(), "epay/video")
                    }

                    // Ensure the directory exists, and create it if necessary
                    if (!directoryPath.exists()) {
                        directoryPath.mkdirs()
                    }

                    // Set the full file path
                    val filePath = File(directoryPath, name + ".mp4").absolutePath
                    put(MediaStore.MediaColumns.DATA, filePath)
                }
            }
            contentValues?.let { contentValues ->


                val mediaStoreOutputOptions = MediaStoreOutputOptions
                    .Builder(
                        requireActivity().contentResolver,
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    )
                    .setContentValues(contentValues)
                    .build()
                recording = videoCapture.output
                    .prepareRecording(binding.root.context, mediaStoreOutputOptions)
                    .apply {
                        if (PermissionChecker.checkSelfPermission(
                                binding.root.context,
                                Manifest.permission.RECORD_AUDIO
                            ) ==
                            PermissionChecker.PERMISSION_GRANTED
                        ) {
                            withAudioEnabled()
                        }
                    }
                    .start(ContextCompat.getMainExecutor(binding.root.context)) { recordEvent ->
                        when (recordEvent) {
                            is VideoRecordEvent.Start -> {
                                Toast.makeText(
                                    binding.root.context,
                                    "Start Record",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }

                            is VideoRecordEvent.Finalize -> {

                                if (!recordEvent.hasError()) {
                                    recordEvent.outputResults.outputUri?.let {
                                        authViewModel.filePath.value = it
                                        authViewModel.videoFile.value?.url=it.toString()
                                    }

                                } else {
                                    recording?.close()
                                    recording = null

                                }

                            }
                        }
                    }
            }
        } catch (e: Exception) {
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun startCountdown() {
        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {

                val secondsRemaining = millisUntilFinished / 1000
                binding.tvTimer.text = "00:00:$secondsRemaining"
            }

            override fun onFinish() {
                // Countdown has finished
                binding.tvTimer.text = "00:00:00"
                if (recording != null) {
                    // Stop the current recording session.
                    recording?.stop()
                    //recording = null

                }
                contentValues?.let {

                    val path = it.get(
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            MediaStore.Video.Media.RELATIVE_PATH
                        } else {
                            MediaStore.MediaColumns.DATA
                        }
                    )


                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                        authViewModel.filePath.value = Uri.parse("$path")

                        authViewModel.videoFile.value?.url= path.toString()
                    }
                    findNavController().navigate(R.id.action_cameraFragment_to_docuploadFragment)
                 //   findNavController().popBackStack()

                }

            }
        }

        countDownTimer.start()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onDestroy() {
        super.onDestroy()
        // Make sure to cancel the countdown timer when the activity is destroyed
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
    }

    private fun dialogRecordingPermission() {
        createAlertDialog(
            requireActivity(),
            "Permission Denied!",
            "Go to setting and enable recording permission",
            "OK", ""
        ) { value ->
            if (value) {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

        if (uri != null) {
            authViewModel?.filePath?.value = uri
            findNavController().popBackStack()
            //findNavController().navigate(R.id.action_homeFragment_to_previewFragment)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }
}