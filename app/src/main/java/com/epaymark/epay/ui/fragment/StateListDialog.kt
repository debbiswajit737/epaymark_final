package com.epaymark.epay.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.adapter.BottomStateListAdapter
import com.epaymark.epay.data.model.BottomSheetStateListModel
import com.epaymark.epay.data.model.OperatorModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.StateBottomsheetLayoutBinding
import com.epaymark.epay.ui.base.BaseBottomSheetFragment
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.`interface`.CallBack

class StateListDialog(val callBack: CallBack) :BaseBottomSheetFragment() {
    lateinit var binding: StateBottomsheetLayoutBinding
    private val viewModel: MyViewModel by activityViewModels()

    var stateList = ArrayList<BottomSheetStateListModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.state_bottomsheet_layout, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun getTheme(): Int {
        return R.style.SheetDialog
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        onViewClick()
    }

    private fun onViewClick() {
        binding.apply {

        }

    }

    private fun setObserver() {

    }

    private fun initView() {

        binding.recycleOperator.apply {

            stateList.add(BottomSheetStateListModel("Andhra Pradesh", false));
            stateList.add(BottomSheetStateListModel("Arunachal Pradesh", false));
            stateList.add(BottomSheetStateListModel("Assam", false));
            stateList.add(BottomSheetStateListModel("Bihar", false));
            stateList.add(BottomSheetStateListModel("Chhattisgarh", false));
            stateList.add(BottomSheetStateListModel("Goa", false));
            stateList.add(BottomSheetStateListModel("Gujarat", false));
            stateList.add(BottomSheetStateListModel("Haryana", false));
            stateList.add(BottomSheetStateListModel("Himachal Pradesh", false));
            stateList.add(BottomSheetStateListModel("Jharkhand", false));
            stateList.add(BottomSheetStateListModel("Karnataka", false));
            stateList.add(BottomSheetStateListModel("Kerala", false));
            stateList.add(BottomSheetStateListModel("Madhya Pradesh", false));
            stateList.add(BottomSheetStateListModel("Maharashtra", false));
            stateList.add(BottomSheetStateListModel("Manipur", false));
            stateList.add(BottomSheetStateListModel("Meghalaya", false));
            stateList.add(BottomSheetStateListModel("Mizoram", false));
            stateList.add(BottomSheetStateListModel("Nagaland", false));
            stateList.add(BottomSheetStateListModel("Odisha", false));
            stateList.add(BottomSheetStateListModel("Punjab", false));
            stateList.add(BottomSheetStateListModel("Rajasthan", false));
            stateList.add(BottomSheetStateListModel("Sikkim", false));
            stateList.add(BottomSheetStateListModel("Tamil Nadu", false));
            stateList.add(BottomSheetStateListModel("Telangana", false));
            stateList.add(BottomSheetStateListModel("Tripura", false));
            stateList.add(BottomSheetStateListModel("Uttar Pradesh", false));
            stateList.add(BottomSheetStateListModel("Uttarakhand", false));
            stateList.add(BottomSheetStateListModel("West Bengal", false));

            adapter= BottomStateListAdapter(stateList, object : CallBack {
                override fun getValue(s: String) {
                    viewModel?.apply {
                        callBack.getValue(s)
                        dismiss()
                    }

                    // callBack.getValue(s)
                    //findNavController().popBackStack()
                }

            })
        }
    }

}