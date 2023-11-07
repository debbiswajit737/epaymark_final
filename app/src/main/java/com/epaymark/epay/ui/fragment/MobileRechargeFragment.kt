package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentMobileRechargeBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isDthOperator
import com.epaymark.epay.utils.`interface`.CallBack

class MobileRechargeFragment : BaseFragment() {
    lateinit var binding: FragmentMobileRechargeBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mobile_recharge, container, false)
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
            viewModel?.prepaitIsActive?.value=true
            llPostPaid.setOnClickListener{
                viewModel?.prepaitIsActive?.value=false
                viewModel?.postpaitIsActive?.value=true
            }

            llPrePaid.setOnClickListener{
                viewModel?.prepaitIsActive?.value=true
                viewModel?.postpaitIsActive?.value=false
            }
            tvOperator.setOnClickListener{
                /*val operatorDialog = OperatorFragment(object : CallBack {
                    override fun getValue(s: String) {
                       viewModel?.operator?.value=s
                    }
                })*/
                activity?.let {act->
                    isDthOperator =false
                    findNavController().navigate(R.id.action_mobileRechargeFragment_to_operatorFragment)
                    //operatorDialog.show(act.supportFragmentManager, operatorDialog.tag)
                }

            }
            btnBrowse.setOnClickListener{
                /*val browserPlainDialog = BrowserPlainFragment(object : CallBack {
                    override fun getValue(s: String) {
                       viewModel?.operator?.value=s
                    }
                })*/
                activity?.let {act->
                    findNavController().navigate(R.id.action_mobileRechargeFragment_to_browserPlainFragment)
                    //browserPlainDialog.show(act.supportFragmentManager, browserPlainDialog.tag)
                }

            }

            btnSubmit.setOnClickListener{
                if (viewModel?.regValidation() == true){
                    Toast.makeText(btnSubmit.context, "ok", Toast.LENGTH_SHORT).show()
                }
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