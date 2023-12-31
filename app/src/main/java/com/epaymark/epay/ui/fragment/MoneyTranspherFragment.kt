package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.AuthViewModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentDthRechargeBinding
import com.epaymark.epay.databinding.FragmentMoneyTranspherBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.popup.CustomPopup.showBindingPopup
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isDthOperator
import com.epaymark.epay.utils.`interface`.CallBack

class MoneyTranspherFragment : BaseFragment() {
    lateinit var binding: FragmentMoneyTranspherBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_money_transpher, container, false)
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

            activity?.let {act->
                btnSubmit.setOnClickListener{
                    if (viewModel?.MoneyTranspherValidation() == true) {
                        findNavController().navigate(R.id.action_moneyTranspherFragment_to_beneficiaryFragment)
                        /*activity?.let {act->
                            val selectTransactionTypeBottomSheetDialog = SelectTransactionTypeBottomSheetDialog(object : CallBack {
                                override fun getValue(s: String) {

                                    val tpinBottomSheetDialog = TpinBottomSheetDialog(object : CallBack {
                                        override fun getValue(s: String) {
                                            Toast.makeText(requireActivity(), "$s", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                    tpinBottomSheetDialog.show(
                                        act.supportFragmentManager,
                                        tpinBottomSheetDialog.tag
                                    )


                                }
                            })
                            selectTransactionTypeBottomSheetDialog.show(
                                act.supportFragmentManager,
                                selectTransactionTypeBottomSheetDialog.tag
                            )
                        }

*/

                    }
                }

            }


        }
    }
    fun initView() {
        binding.apply {

        }

    }

    fun setObserver() {
        binding?.apply {
            viewModel?.mobileSendMoney?.observe(viewLifecycleOwner){
                viewModel?.sendMoneyVisibility?.value = it.length==10
                // need to check from api
            }
        }

    }


}