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
import com.epaymark.epay.databinding.FragmentGasBookingBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.popup.CustomPopup.showBindingPopup
import com.epaymark.epay.ui.receipt.DthReceptDialogFragment
import com.epaymark.epay.ui.receipt.MobileReceptDialogFragment
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isDthOperator
import com.epaymark.epay.utils.`interface`.CallBack
import java.util.Objects

class GasBookingFragment : BaseFragment() {
    lateinit var binding: FragmentGasBookingBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gas_booking, container, false)
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
                context?.let {ctx->
                    viewModel?.userName?.value = "Sample user"
                    viewModel?.balence?.value = "1009"
                    viewModel?.nextRecharge?.value = "01-01-2023"
                    viewModel?.monthly?.value = "789"
                    viewModel?.type?.value = "Active"
                    binding.cardUserDetails.visibility=View.VISIBLE
                }
            }

            btnSubmit.setOnClickListener{
                if (viewModel?.dthValidation() == true){
                    val tpinBottomSheetDialog = TpinBottomSheetDialog(object : CallBack {
                        override fun getValue(s: String) {
                            val dialogFragment = DthReceptDialogFragment(object: CallBack {
                                override fun getValue(s: String) {
                                    if (Objects.equals(s,"back")) {
                                        findNavController().popBackStack()
                                    }
                                }
                            })
                            dialogFragment.show(childFragmentManager, dialogFragment.tag)
                        }
                    })
                    activity?.let {act->
                        tpinBottomSheetDialog.show(act.supportFragmentManager, tpinBottomSheetDialog.tag)
                    }
                }
            }
            etOperator.setOnClickListener {
                rlOperator.performClick()
            }
            rlOperator.setOnClickListener{
                activity?.let {act->
                    isDthOperator=true
                    findNavController().navigate(R.id.action_DTHRechargeFragment_to_operatorFragment)
                }

            }
        }

        binding.imgClose.setOnClickListener{
            binding.cardUserDetails.visibility=View.GONE
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