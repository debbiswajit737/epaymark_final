package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentDthRechargeBinding
import com.epaymark.epay.databinding.FragmentFastTagBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.popup.CustomPopup.showBindingPopup
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isDthOperator
import com.epaymark.epay.utils.`interface`.CallBack

class FastTagFragment : BaseFragment() {
    lateinit var binding: FragmentFastTagBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fast_tag, container, false)
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

            imgBack.back()

            btnCustomerInfo.setOnClickListener{
                activity?.let {act->
                    val fasttagOperatorListBottomSheetDialog = FasttagOperatorListBottomSheetDialog(object : CallBack {
                        override fun getValue(s: String) {
                            Toast.makeText(requireActivity(), "$s", Toast.LENGTH_SHORT).show()
                        }
                    })
                    fasttagOperatorListBottomSheetDialog.show(
                        act.supportFragmentManager,
                        fasttagOperatorListBottomSheetDialog.tag
                    )
                }
            }

            btnSubmit.setOnClickListener{
                if (viewModel?.fastTagValidation() == true){
                    Toast.makeText(btnSubmit.context, "ok", Toast.LENGTH_SHORT).show()
                }
            }
            etOperator.setOnClickListener {
                rlOperator.performClick()
            }

        }



    }

    fun initView() {
        binding.apply {
            etAmt.setupAmount()
        }

    }

    fun setObserver() {

    }


}