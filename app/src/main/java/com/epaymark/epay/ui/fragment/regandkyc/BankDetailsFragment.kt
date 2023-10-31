package com.epaymark.epay.ui.fragment.regandkyc


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.databinding.BankDetailsFragmentBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.CallBack

class BankDetailsFragment : BaseFragment() {
    lateinit var binding: BankDetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bank_details_fragment, container, false)
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
           findNavController().navigate(R.id.action_bankDetailsFragment_to_docuploadFragment)
        }

    }

    fun initView() {
        binding.apply {
            spinnerBank.apply {
                val bankArray = arrayOf("Select Bank", "A B Bank Limited","Andhra Bank")
                adapter = ArrayAdapter<String>(this.context, R.layout.custom_spinner_item, bankArray)
                setSpinner(object : CallBack {
                    override fun getValue(s: String) {
                        // Toast.makeText(binding.root.context, "$s", Toast.LENGTH_SHORT).show()
                    }
                },bankArray)
            }
        }

    }

    fun setObserver() {

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
}