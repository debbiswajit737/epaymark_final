package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.adapter.AdminBankListAdapter
import com.epaymark.epay.adapter.BankListAdapter
import com.epaymark.epay.adapter.UserDetailsAdapter
import com.epaymark.epay.data.model.AdminBankListModel
import com.epaymark.epay.data.model.BankListModel
import com.epaymark.epay.data.model.UserDetails
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentCashWithdrawBinding
import com.epaymark.epay.databinding.FragmentEpotlyBinding
import com.epaymark.epay.databinding.FragmentMobileRechargeBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.receipt.BalenceEnquaryReceptDialogFragment
import com.epaymark.epay.ui.receipt.CashWithdrawReceptDialogFragment
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isCashWithdraw
import com.epaymark.epay.utils.helpers.Constants.isDthOperator
import com.epaymark.epay.utils.`interface`.CallBack
import com.epaymark.epay.utils.`interface`.CallBack4
import com.google.zxing.WriterException
import java.util.Objects

class CashWithdrawFragment : BaseFragment() {
    lateinit var binding: FragmentCashWithdrawBinding
    private val viewModel: MyViewModel by activityViewModels()
    var userDetailsList = ArrayList<UserDetails>()
    var bankList = ArrayList<AdminBankListModel>()
    var adminBankListAdapter:AdminBankListAdapter?=null
    var selectBank=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cash_withdraw, container, false)
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
            etProvidedAmount.setupAmount()



            btnSubmit.setOnClickListener{
                activity?.let {act->
                    if (viewModel?.cashWithdrawValidation() == true){
                        /*val tpinBottomSheetDialog = TpinBottomSheetDialog(object : CallBack {
                            override fun getValue(s: String) {
                                if (s=="123456"){
                                    Toast.makeText(requireActivity(), "ok", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                        tpinBottomSheetDialog.show(act.supportFragmentManager, tpinBottomSheetDialog.tag)
                        */
                        val tpinBottomSheetDialog = TpinBottomSheetDialog(object : CallBack {
                            override fun getValue(s: String) {
                                val dialogFragment = CashWithdrawReceptDialogFragment(object: CallBack {
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

            }
            btnCashWithdraw.setOnClickListener {


                if (viewModel?.cashWithdrawalValidation()==true){
                    if (selectBank.isNotEmpty()) {
                        activity?.let { act ->
                            val aadharAuthBottomSheetDialog =
                                AadharAuthBottomSheetDialog(object : CallBack {
                                    override fun getValue(s: String) {

                                    }
                                })
                            aadharAuthBottomSheetDialog.show(
                                act.supportFragmentManager,
                                aadharAuthBottomSheetDialog.tag
                            )
                        }
                    }
                    else{
                        Toast.makeText(requireActivity(), "Select Bank", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }



    }

    fun initView() {
        binding.apply {
            etAmt.setupAmount()

            group1.isVisible= isCashWithdraw
            group2.isVisible=!group1.isVisible
            binding.recycleViewUserdetails.apply {
                userDetailsList.clear()
                userDetailsList.add(UserDetails("Name","Test User"))
                userDetailsList.add(UserDetails("Outlet Name","Test Outlet Name"))
                userDetailsList.add(UserDetails("Mobile Number","9999999999"))
                userDetailsList.add(UserDetails("Email Id","test@test.com"))
                userDetailsList.add(UserDetails("Address","123, Park Street,Kolkata - 700001,West Bengal, India"))

                adapter= UserDetailsAdapter(userDetailsList)
            }

            binding.recycleViewBankList.apply {
                bankList.add(AdminBankListModel(R.drawable.axix_bank_logo,"AXIX Bank",false))
                bankList.add(AdminBankListModel(R.drawable.icici,"ICICI Bank",false))


                adminBankListAdapter= AdminBankListAdapter(bankList, object : CallBack4 {
                    override fun getValue4(s1: String, s2: String, s3: String, s4: String) {
                        selectBank=s1
                    }



                })
                adapter=adminBankListAdapter
            }


        }
    }

    fun setObserver() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adminBankListAdapter?.filter?.filter(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }


}