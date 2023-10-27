package com.epaymark.epay.ui.fragment


import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.epaymark.epay.R
import com.epaymark.epay.adapter.StateListAdapter
import com.epaymark.epay.data.model.ListIcon
import com.epaymark.epay.databinding.FragmentRegBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.CallBack
import java.util.Calendar

class RegFragment : BaseFragment() {
    lateinit var binding: FragmentRegBinding
    var stateList = ArrayList<String>()
    var stateListAdapter:StateListAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reg, container, false)
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
            spinnerGender.apply {
                val genderArray = arrayOf("Male", "Female")
                adapter = ArrayAdapter<String>(this.context, R.layout.custom_spinner_item, genderArray)
                setSpinner(object :CallBack{
                override fun getValue(s: String) {
                   // Toast.makeText(binding.root.context, "$s", Toast.LENGTH_SHORT).show()
                }
            },genderArray)
            }
            tvDob.setOnClickListener {
                it.showDatePickerDialog(object :CallBack{
                    override fun getValue(s: String) {
                        tvDob.text = s
                    }

                })
            }

        }
    }

    fun initView() {
        // Add all the states
        stateList.add("Andhra Pradesh")
        stateList.add("Arunachal Pradesh")
        stateList.add("Assam")
        stateList.add("Bihar")
        stateList.add("Chhattisgarh")
        stateList.add("Goa")
        stateList.add("Gujarat")
        stateList.add("Haryana")
        stateList.add("Himachal Pradesh")
        stateList.add("Jharkhand")
        stateList.add("Karnataka")
        stateList.add("Kerala")
        stateList.add("Madhya Pradesh")
        stateList.add("Maharashtra")
        stateList.add("Manipur")
        stateList.add("Meghalaya")
        stateList.add("Mizoram")
        stateList.add("Nagaland")
        stateList.add("Odisha")
        stateList.add("Punjab")
        stateList.add("Rajasthan")
        stateList.add("Sikkim")
        stateList.add("Tamil Nadu")
        stateList.add("Telangana")
        stateList.add("Tripura")
        stateList.add("Uttar Pradesh")
        stateList.add("Uttarakhand")
        stateList.add("West Bengal")

// Add all the union territories
        stateList.add("Andaman and Nicobar Islands")
        stateList.add("Chandigarh")
        stateList.add("Dadra and Nagar Haveli and Daman and Diu")
        stateList.add("Lakshadweep")
        stateList.add("Delhi (National Capital Territory of Delhi)")
        stateList.add("Puducherry")
        stateList.add("Ladakh")
        stateList.add("Jammu and Kashmir")
        binding.recycleState.apply {
            binding.tvState.setOnClickListener {

                if (binding.recycleState.isVisible){
                    binding.recycleState.visibility = View.GONE
                    binding.etState.visibility = View.GONE
                }
                else {
                    binding.recycleState.visibility = View.VISIBLE
                    binding.etState.visibility = View.VISIBLE
                }
            }
            stateListAdapter= StateListAdapter(stateList,object :CallBack{
                override fun getValue(s: String) {
                    binding.tvState.text = s
                    binding.recycleState.visibility=View.GONE
                    binding.etState.visibility = View.GONE
                }

            })
        adapter=stateListAdapter


            binding.etState.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    stateListAdapter?.filter?.filter(s)
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
    }

    fun setObserver() {

    }

    private fun View.showDatePickerDialog(callBack: CallBack) {
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