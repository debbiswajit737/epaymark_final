package com.epaymark.epay.ui.fragment


import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.adapter.UserDetailsAdapter
import com.epaymark.epay.adapter.UtilityAdapter
import com.epaymark.epay.adapter.ViewMoreAdapter
import com.epaymark.epay.data.model.ListIcon
import com.epaymark.epay.data.model.UserDetails
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentUserDetailsBinding
import com.epaymark.epay.databinding.FragmentViewMoreBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.fragment.fragmentDialog.GasBillerListDialog
import com.epaymark.epay.utils.*
import com.epaymark.epay.utils.common.MethodClass.userLogout
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isFromUtilityPage
import com.epaymark.epay.utils.helpers.Constants.searchValue
import com.epaymark.epay.utils.`interface`.CallBack
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter


class ViewMoreFragment : BaseFragment() {
    lateinit var binding: FragmentViewMoreBinding
    private val viewModel: MyViewModel by activityViewModels()
    var userDetailsList = ArrayList<UserDetails>()
    var utilityBillList = ArrayList<ListIcon>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_more, container, false)
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

    override fun onResume() {
        super.onResume()
        isFromUtilityPage=true
    }
    private fun onViewClick() {

        binding.apply {
          imgBack.back()

          }
        }


    fun initView() {

            binding.recycleUtility.apply {
            utilityBillList.clear()
            utilityBillList.add(ListIcon(getString(R.string.education_fees), R.drawable.education_fee))
            utilityBillList.add(ListIcon(getString(R.string.broadband), R.drawable.boradband))
            utilityBillList.add(ListIcon(getString(R.string.gas_booking), R.drawable.gas_booking_ioc))
            utilityBillList.add(ListIcon(getString(R.string.loan_payment), R.drawable.loan_ioc_viewmore))

            //utilityBillList.add(ListIcon(getString(R.string.view_more), R.drawable.view_more))
            adapter= ViewMoreAdapter(utilityBillList,R.drawable.circle_shape2, object : CallBack {
                override fun getValue(s: String) {
                    searchValue =s
                    viewModel.from_page_message.value="view_more"
                    serviceNavigation(s)
                    //findNavController().popBackStack()

                    /*when(s){

                        getString(R.string.electric)->{
                            activity?.let {act->
                                val stateListDialog = StateListDialog(object : CallBack {
                                    override fun getValue(s: String) {
                                        viewModel?.state?.value=s
                                        findNavController().navigate(R.id.action_homeFragment2_to_electricRechargeFragment)
                                    }

                                })
                                stateListDialog.show(act.supportFragmentManager, stateListDialog.tag)

                            }
                        }
                        getString(R.string.view_more)->{}

                    }*/
                }

            })
        }
    }

    fun setObserver() {
        binding.apply {

        }

    }

    private fun serviceNavigation(s: String) {
        when(s){
            //findNavController().navigate(R.id.action_viewMoreFragment_to_formFragment)
            getString(R.string.education_fees)->{
                findNavController().navigate(R.id.action_viewMoreFragment_to_educationFeesFragment)
            }

            getString(R.string.broadband)->{
                findNavController().navigate(R.id.action_viewMoreFragment_to_broadBandFragment)
            }

            getString(R.string.gas_booking)->{
                activity?.let {act->
                    val gasBillerListDialog = GasBillerListDialog(object : CallBack {
                        override fun getValue(s: String) {
                            viewModel?.gasBiller?.value=s
                            findNavController().navigate(R.id.action_viewMoreFragment_to_gasBookingFragment)
                        }

                    })
                    gasBillerListDialog.show(act.supportFragmentManager, gasBillerListDialog.tag)

                }

            }

            getString(R.string.loan_payment)->{
                findNavController().navigate(R.id.action_viewMoreFragment_to_loanPaymentFragment)
            }



        }


    }
}