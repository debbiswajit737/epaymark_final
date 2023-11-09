package com.epaymark.epay.ui.fragment.regandkyc


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.AuthViewModel
import com.epaymark.epay.databinding.BankDetailsFragmentBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.fragment.CameraDialog
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.`interface`.CallBack

class BankDetailsFragment : BaseFragment() {
    lateinit var binding: BankDetailsFragmentBinding
    private val authViewModel: AuthViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bank_details_fragment, container, false)
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

    private fun onViewClick() {
        binding.btnSaveContinue.setOnClickListener {
            if (authViewModel.bankDetailsValidation()) {
                findNavController().navigate(R.id.action_bankDetailsFragment_to_docuploadFragment)
             }
            }

        binding.llCheck.setOnClickListener{
            Constants.isBackCamera =true

            Constants.isPdf =false
            val cameraDialog = CameraDialog(object : CallBack {
                override fun getValue(s: String) {

                    getImage(s)
                }

            })
           activity?.let { act->  cameraDialog.show(act.supportFragmentManager, cameraDialog.tag)}
        }
    }

    fun initView() {
        binding.apply {
            spinnerBank.apply {
                val bankArray = arrayOf("Select Bank", "A B Bank Limited","Andhra Bank")
                adapter = ArrayAdapter<String>(this.context, R.layout.custom_spinner_item, bankArray)
                setSpinner(object : CallBack {
                    override fun getValue(s: String) {
                        authViewModel.bankName.value=s
                        // Toast.makeText(binding.root.context, "$s", Toast.LENGTH_SHORT).show()
                    }
                },bankArray)
            }
        }

    }

    fun setObserver() {
        authViewModel.filePath.observe(viewLifecycleOwner){
         authViewModel.pancardImage3.value=it.toString()
         Log.d("TAG_file", "true setObserver: "+it.uriToBase64(binding.root.context.contentResolver))
        }
    }

    fun Spinner.setSpinner(callBack: CallBack, genderArray: Array<String>){
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                callBack.getValue(genderArray[position])
                // val selectedItem = items[position]
                // Handle the selected item
                //Toast.makeText(this@MainActivity, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle when nothing is selected
            }
        }
    }

    private fun getImage(s:String) {
        when(s){
            "g"->{
                Constants.isVideo =false
                Constants.isGallary =true
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                //findNavController().navigate(R.id.action_bankDetailsFragment_to_cameraFragment)
            }
            "t"->{
                Constants.isVideo =false
                Constants.isGallary =false
                findNavController().navigate(R.id.action_bankDetailsFragment_to_cameraFragment)
            }

        }
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

        if (uri != null) {
            authViewModel?.cancleCheck?.value = uri.toString()

            //findNavController().navigate(R.id.action_homeFragment_to_previewFragment)
        } else {
            authViewModel?.cancleCheck?.value = "/"

            Log.d("PhotoPicker", "No media selected")
        }

    }
}