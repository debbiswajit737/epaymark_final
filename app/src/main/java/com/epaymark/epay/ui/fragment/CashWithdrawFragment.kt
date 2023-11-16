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
import com.epaymark.epay.adapter.UserDetailsAdapter
import com.epaymark.epay.data.model.UserDetails
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentCashWithdrawBinding
import com.epaymark.epay.databinding.FragmentEpotlyBinding
import com.epaymark.epay.databinding.FragmentMobileRechargeBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isDthOperator
import com.epaymark.epay.utils.`interface`.CallBack
import com.google.zxing.WriterException

class CashWithdrawFragment : BaseFragment() {
    lateinit var binding: FragmentCashWithdrawBinding
    private val viewModel: MyViewModel by activityViewModels()
    var userDetailsList = ArrayList<UserDetails>()
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




            btnSubmit.setOnClickListener{
                activity?.let {act->
                    if (viewModel?.cashWithdrawValidation() == true){
                        val tpinBottomSheetDialog = TpinBottomSheetDialog(object : CallBack {
                            override fun getValue(s: String) {
                                if (s=="123456"){
                                    Toast.makeText(requireActivity(), "ok", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                        tpinBottomSheetDialog.show(act.supportFragmentManager, tpinBottomSheetDialog.tag)

                    }
                }

            }

        }



    }

    fun initView() {
        binding.apply {
            etAmt.setupAmount()



            binding.recycleViewUserdetails.apply {
                userDetailsList.clear()
                userDetailsList.add(UserDetails("Name","Test User"))
                userDetailsList.add(UserDetails("Outlet Name","Test Outlet Name"))
                userDetailsList.add(UserDetails("Mobile Number","9999999999"))
                userDetailsList.add(UserDetails("Email Id","test@test.com"))
                userDetailsList.add(UserDetails("Address","123, Park Street,Kolkata - 700001,West Bengal, India"))

                adapter= UserDetailsAdapter(userDetailsList)
            }
        }
    }

    fun setObserver() {

    }


}