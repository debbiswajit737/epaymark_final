package com.epaymark.epay.ui.fragment.fragmentDialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.OperatorListAdapter
import com.epaymark.epay.data.model.OperatorListModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentInstituteListDialogBinding
import com.epaymark.epay.databinding.FragmentOperatorlistDialogBinding
import com.epaymark.epay.ui.activity.DashboardActivity
import com.epaymark.epay.ui.base.BaseCenterSheetFragment
import com.epaymark.epay.utils.`interface`.CallBack
import com.epaymark.epay.utils.`interface`.CallBack4


class InstituteListDialogFragment(val callBack: CallBack) : BaseCenterSheetFragment() {
    lateinit var binding: FragmentInstituteListDialogBinding
    private val viewModel: MyViewModel by activityViewModels()
    var operatorListModel = ArrayList<OperatorListModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_institute_list_dialog, container, false)
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
            imgBack.setOnClickListener{
                dismiss()
            }

          }

        }


    fun initView() {
        setCrdViewMinHeight()
        binding.recycleViewOperatpr.apply {
            operatorListModel.add(OperatorListModel(R.drawable.institute_type,"Tuition Fees"))
            operatorListModel.add(OperatorListModel(R.drawable.institute_type,"School Fees"))
            operatorListModel.add(OperatorListModel(R.drawable.institute_type,"Hostel Fees"))
            operatorListModel.add(OperatorListModel(R.drawable.institute_type,"College Fees"))
            operatorListModel.add(OperatorListModel(R.drawable.institute_type,"Correspondence Schools"))
            operatorListModel.add(OperatorListModel(R.drawable.institute_type,"Business Schools"))
            operatorListModel.add(OperatorListModel(R.drawable.institute_type,"Vocational/Trade Schools"))
            adapter= OperatorListAdapter(operatorListModel, object : CallBack4 {
                override fun getValue4(s1: String, s2: String, s3: String, s4: String) {
                    viewModel?.selectInstitute?.value=s1



                    dismiss()
                }
            })
        }

    }

    private fun setCrdViewMinHeight() {
    }

    fun setObserver() {
        binding.apply {

        }

    }
}