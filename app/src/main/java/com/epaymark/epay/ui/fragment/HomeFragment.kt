package com.epaymark.epay.ui.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.epaymark.epay.adapter.HorizontalMarginItemDecoration
import com.epaymark.epay.R
import com.epaymark.epay.adapter.AEPSAdapter
import com.epaymark.epay.adapter.AutoScrollHandler
import com.epaymark.epay.adapter.BannerViewpagerAdapter
import com.epaymark.epay.adapter.EssentialAdapter
import com.epaymark.epay.adapter.ReportAdapter
import com.epaymark.epay.adapter.TravelAdapter
import com.epaymark.epay.adapter.BankingAdapter
import com.epaymark.epay.adapter.FinancialAdapter
import com.epaymark.epay.adapter.MostPopularAdapter
import com.epaymark.epay.adapter.UtilityAdapter
import com.epaymark.epay.data.model.ListIcon
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentHomeBinding


import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.popup.CustomPopup.showBalencePopup
import com.epaymark.epay.utils.common.MethodClass.userLogout
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isCashWithdraw
import com.epaymark.epay.utils.helpers.Constants.isFromSearchPage
import com.epaymark.epay.utils.helpers.Constants.searchList
import com.epaymark.epay.utils.helpers.Constants.searchValue
import com.epaymark.epay.utils.`interface`.CallBack


class HomeFragment : BaseFragment() {
    private var isRotated = true
    private var isRotated2 = true
    private var isRotated3 = true
    private var isRotated4 = true
    private var isRotated5 = true
    var iconList = ArrayList<ListIcon>()
    var iconList2 = ArrayList<ListIcon>()
    var iconList3 = ArrayList<ListIcon>()
    var iconList4 = ArrayList<ListIcon>()
    var iconList5 = ArrayList<ListIcon>()
    var iconList6 = ArrayList<ListIcon>()
    var iconList7 = ArrayList<ListIcon>()
    var iconList8 = ArrayList<ListIcon>()
    var iconList9 = ArrayList<ListIcon>()
    var iconList10 = ArrayList<ListIcon>()
    var iconList11 = ArrayList<ListIcon>()
    var iconList12 = ArrayList<ListIcon>()
    lateinit var binding: FragmentHomeBinding
    private lateinit var autoScrollHandler: AutoScrollHandler
    private val viewModel: MyViewModel by activityViewModels()
    var deviceHeight:Int=0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this*/

        binding = FragmentHomeBinding.inflate(layoutInflater)
        getDeviceWIDTHandHeight()
        val view = binding.root
        init()
        viewOnClick()

        return view
    }

    private fun getDeviceWIDTHandHeight() {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager?.defaultDisplay?.getMetrics(displayMetrics)

        //var width = displayMetrics.widthPixels
        deviceHeight = displayMetrics.heightPixels

        Log.d("TAG_deviceHeight", "getDeviceWIDTHandHeight: $deviceHeight")

    }

    private fun serviceNavigation(s: String) {
        when(s){
            //recycleViewEpayBanking
            getString(R.string.scan)->{
                findNavController().navigate(R.id.action_homeFragment2_to_QRCodeFragment)
            }

            getString(R.string.ePotly)->{
                findNavController().navigate(R.id.action_homeFragment2_to_epotlyFragment)
            }

            getString(R.string.payment_request)->{
                findNavController().navigate(R.id.action_homeFragment2_to_paymentRequestFragment)
            }
            getString(R.string.move_to_wallet)->{
                findNavController().navigate(R.id.action_homeFragment2_to_moveToWalletFragment)
            }
            getString(R.string.move_to_bank)->{
                findNavController().navigate(R.id.action_homeFragment2_to_moveToBankFragment)
            }


            //recycleAccount

            getString(R.string.myaccount)->{
                findNavController().navigate(R.id.action_homeFragment2_to_userDetailsFragment)
            }
            getString(R.string.support)->{
                findNavController().navigate(R.id.action_homeFragment2_to_supportFragment)
            }
            getString(R.string.likeus)->{}
            getString(R.string.usage_terms)->{
                findNavController().navigate(R.id.action_homeFragment2_to_termsAndConditionFragment)
            }
            getString(R.string.password)->{
                findNavController().navigate(R.id.action_homeFragment2_to_changePasswordFragment)
            }
            getString(R.string.certificate)->{
                findNavController().navigate(R.id.action_homeFragment2_to_certificateFragment)
            }
            getString(R.string.logout)->{
                context?.let { ctx->
                    ctx.userLogout()
                }
            }



            //recycleViewEpayBanking

            getString(R.string.balance) -> {
                showBalencePopup(binding.root.context)
            }

            getString(R.string.cash_withdraw) -> {
                isCashWithdraw=true
                findNavController().navigate(R.id.action_homeFragment2_to_cashWithdrawFragment)
            }


            getString(R.string.mini_statement) -> {
                viewModel.reportType.value=getString(R.string.dmt)
                findNavController().navigate(R.id.action_homeFragment2_to_miniStatementFragment)
                // findNavController().navigate(R.id.action_homeFragment2_to_cashWithdrawFragment)
            }

            getString(R.string.aadhar_pay) -> {
                isCashWithdraw=false
                findNavController().navigate(R.id.action_homeFragment2_to_cashWithdrawFragment)
            }



            //recycleEssential
            getString(R.string.prepaid)->{
                viewModel.prepaitOrPostPaid.value="Prepaid"
                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
            }
            getString(R.string.postpaid)->{
                viewModel.prepaitOrPostPaid.value="Postpaid"
                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
            }

            getString(R.string.dth_recharge)->{
                findNavController().navigate(R.id.action_homeFragment2_to_DTHRechargeFragment)
            }


            //recycleUtility

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

            //recycleFinancial



            getString(R.string.prepaid)->{
                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
            }
            getString(R.string.postpaid)->{
                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
            }

            getString(R.string.dth_recharge)->{
                findNavController().navigate(R.id.action_homeFragment2_to_DTHRechargeFragment)
            }
            getString(R.string.money_transfer)->{
                findNavController().navigate(R.id.action_homeFragment2_to_moneyTranspherFragment)
            }
            getString(R.string.credit_card)->{
                findNavController().navigate(R.id.action_homeFragment2_to_creditCardPaymentFragment)
            }



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



        }


    }

    private fun viewOnClick() {
        binding.apply {
            imgBalance.setOnClickListener{
                showBalencePopup(binding.root.context)
            }
            imgSearch.setOnClickListener{
                        searchList.clear()
                        //searchList.addAll(iconList)
                        //searchList.addAll(iconList2)
                        searchList.addAll(iconList3)
                        //searchList.addAll(iconList4)
                        searchList.addAll(iconList5)
                        searchList.addAll(iconList6)
                        searchList.addAll(iconList7)
                        //searchList.addAll(iconList8)
                        searchList.addAll(iconList9)
                        searchList.addAll(iconList10)
                        searchList.addAll(iconList11)
                        searchList.addAll(iconList12)
                findNavController().navigate(R.id.action_homeFragment2_to_searchFragment)
            }

            rotateView(arrowImageView, 0f)
            val collapseAnimation: Animation =
                AnimationUtils.loadAnimation(requireActivity(), R.anim.collapse_animation)
            arrowImageView.setOnClickListener {

                if (isRotated) {

                    rotateView(arrowImageView, 0f)


                    val layoutParams = llContainer.layoutParams
                    layoutParams.height = 40
                    llContainer.layoutParams = layoutParams
                    llContainer.startAnimation(collapseAnimation)

                    llContainer.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageView, 180f)
                    val layoutParams = llContainer.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    llContainer.layoutParams = layoutParams
                    llContainer.visibility = View.VISIBLE
                    llContainer.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated = !isRotated
            }



        /*    arrowImageView2.setOnClickListener {

                if (isRotated2) {

                    rotateView(arrowImageView2, 0f)


                    val layoutParams = linearLayout.layoutParams
                    layoutParams.height = 40
                    linearLayout.layoutParams = layoutParams
                    linearLayout.startAnimation(collapseAnimation)

                    linearLayout.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageView2, 180f)
                    val layoutParams = linearLayout.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    linearLayout.layoutParams = layoutParams
                    linearLayout.visibility = View.VISIBLE
                    linearLayout.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated2 = !isRotated2
            }
*/



            arrowImageViewNew2Aeps.setOnClickListener {

                if (isRotated2) {

                    rotateView(arrowImageViewNew2Aeps, 0f)


                    val layoutParams = recycleAEPS.layoutParams
                    layoutParams.height = 40
                    recycleAEPS.layoutParams = layoutParams
                    recycleAEPS.startAnimation(collapseAnimation)

                    recycleAEPS.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageViewNew2Aeps, 180f)
                    val layoutParams = recycleAEPS.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    recycleAEPS.layoutParams = layoutParams
                    recycleAEPS.visibility = View.VISIBLE
                    recycleAEPS.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated2 = !isRotated2
            }


            arrowImageViewNewEssential.setOnClickListener {

                if (isRotated3) {

                    rotateView(arrowImageViewNewEssential, 0f)


                    val layoutParams = recycleEssential.layoutParams
                    layoutParams.height = 40
                    recycleEssential.layoutParams = layoutParams
                    recycleEssential.startAnimation(collapseAnimation)

                    recycleEssential.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageViewNewEssential, 180f)
                    val layoutParams = recycleEssential.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    recycleEssential.layoutParams = layoutParams
                    recycleEssential.visibility = View.VISIBLE
                    recycleEssential.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated3 = !isRotated3
            }

            arrowImageViewNewMostUses.setOnClickListener {

                if (isRotated3) {

                    rotateView(arrowImageViewNewMostUses, 0f)


                    val layoutParams = recycleMostUses.layoutParams
                    layoutParams.height = 40
                    recycleMostUses.layoutParams = layoutParams
                    recycleMostUses.startAnimation(collapseAnimation)

                    recycleMostUses.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageViewNewMostUses, 180f)
                    val layoutParams = recycleMostUses.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    recycleMostUses.layoutParams = layoutParams
                    recycleMostUses.visibility = View.VISIBLE
                    recycleMostUses.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated3 = !isRotated3
            }
            /*arrowImageViewNew3.setOnClickListener {

                if (isRotated3) {

                    rotateView(arrowImageViewNew3, 0f)


                    val layoutParams = linearLayoutNew3.layoutParams
                    layoutParams.height = 40
                    linearLayoutNew3.layoutParams = layoutParams
                    linearLayoutNew3.startAnimation(collapseAnimation)

                    linearLayoutNew3.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageViewNew3, 180f)
                    val layoutParams = linearLayoutNew3.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    linearLayoutNew3.layoutParams = layoutParams
                    linearLayoutNew3.visibility = View.VISIBLE
                    linearLayoutNew3.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated3 = !isRotated3
            }*/


            arrowImageViewNew4.setOnClickListener {

                if (isRotated4) {

                    rotateView(arrowImageViewNew4, 0f)


                    val layoutParams = linearLayoutNew4.layoutParams
                    layoutParams.height = 40
                    linearLayoutNew4.layoutParams = layoutParams
                    linearLayoutNew4.startAnimation(collapseAnimation)

                    linearLayoutNew4.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageViewNew4, 180f)
                    val layoutParams = linearLayoutNew4.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    linearLayoutNew4.layoutParams = layoutParams
                    linearLayoutNew4.visibility = View.VISIBLE
                    linearLayoutNew4.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated4 = !isRotated4
            }

            arrowImageViewNew44.setOnClickListener {

                if (isRotated5) {

                    rotateView(arrowImageViewNew44, 0f)


                    val layoutParams = recycleFinancial.layoutParams
                    layoutParams.height = 40
                    recycleFinancial.layoutParams = layoutParams
                    recycleFinancial.startAnimation(collapseAnimation)

                    recycleFinancial.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageViewNew4, 180f)
                    val layoutParams = recycleFinancial.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    recycleFinancial.layoutParams = layoutParams
                    recycleFinancial.visibility = View.VISIBLE
                    recycleFinancial.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated5 = !isRotated5
            }




            recycleEssential.apply {
                iconList3.clear()
                iconList3.add(ListIcon(getString(R.string.prepaid), R.drawable.db_mobile))
                iconList3.add(ListIcon(getString(R.string.postpaid), R.drawable.db_mobile))
                iconList3.add(ListIcon(getString(R.string.dth_recharge), R.drawable.ic_dth_recharge))
                /*iconList3.add(ListIcon(getString(R.string.electric), R.drawable.electric))
                iconList3.add(ListIcon("Fast Tag", R.drawable.icons8_fastag))
                iconList3.add(ListIcon("Google Play", R.drawable.google_play))*/
                iconList3.add(ListIcon(getString(R.string.insurance), R.drawable.insurance))
                /*iconList3.add(ListIcon("Water", R.drawable.water))
                iconList3.add(ListIcon("View More", R.drawable.view_more))*/
                adapter= EssentialAdapter(iconList3,R.drawable.circle_shape2, object : CallBack {
                    override fun getValue(s: String) {
                        serviceNavigation(s)
                        /*when(s){
                            getString(R.string.prepaid)->{
                                viewModel.prepaitOrPostPaid.value="Prepaid"
                                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
                            }
                            getString(R.string.postpaid)->{
                                viewModel.prepaitOrPostPaid.value="Postpaid"
                                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
                            }

                            getString(R.string.dth_recharge)->{
                                findNavController().navigate(R.id.action_homeFragment2_to_DTHRechargeFragment)
                            }




                        }*/
                    }

                })
            }




            recycleFinancial.apply {
                iconList11.clear()
                iconList11.add(ListIcon(getString(R.string.credit_card), R.drawable.credit_card))
                iconList11.add(ListIcon(getString(R.string.cash_collection), R.drawable.cash_collection))
                iconList11.add(ListIcon(getString(R.string.matm), R.drawable.matm))
                iconList11.add(ListIcon(getString(R.string.money_transfer), R.drawable.imps))
                /*iconList3.add(ListIcon("Water", R.drawable.water))
                iconList3.add(ListIcon("View More", R.drawable.view_more))*/
                adapter= FinancialAdapter(iconList11,R.drawable.circle_shape2, object : CallBack {
                    override fun getValue(s: String) {
                        serviceNavigation(s)
                        /*when(s){
                            getString(R.string.prepaid)->{
                                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
                            }
                            getString(R.string.postpaid)->{
                                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
                            }

                            getString(R.string.dth_recharge)->{
                                findNavController().navigate(R.id.action_homeFragment2_to_DTHRechargeFragment)
                            }
                            getString(R.string.money_transfer)->{
                                findNavController().navigate(R.id.action_homeFragment2_to_moneyTranspherFragment)
                            }
                            getString(R.string.credit_card)->{
                                findNavController().navigate(R.id.action_homeFragment2_to_creditCardPaymentFragment)
                            }



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


                        }*/
                    }

                })
            }


            recycleUtility.apply {
                iconList10.clear()
                iconList10.add(ListIcon(getString(R.string.electric), R.drawable.electric))
                iconList10.add(ListIcon(getString(R.string.gas), R.drawable.gas_ioc))
                iconList10.add(ListIcon(getString(R.string.fast_tag), R.drawable.icons8_fastag))
                iconList10.add(ListIcon(getString(R.string.view_more), R.drawable.view_more))
                adapter= UtilityAdapter(iconList10,R.drawable.circle_shape2, object : CallBack {
                    override fun getValue(s: String) {
                        serviceNavigation(s)
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




            recycleMostUses.apply {
                requestFocus()
                iconList12.clear()
                iconList12.add(ListIcon(getString(R.string.electric), R.drawable.electric))

                adapter= MostPopularAdapter(iconList12,R.drawable.circle_shape2, object : CallBack {
                    override fun getValue(s: String) {
                        when(s){

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

                        }
                    }

                })
            }









           /* recycleMyBig.apply {
                iconList4.clear()
                iconList4.add(ListIcon(getString(R.string.balance), R.drawable.transaction_history))
                iconList4.add(ListIcon("CMS", R.drawable.cms))
                iconList4.add(ListIcon("Wallet", R.drawable.db_balance))
                iconList4.add(ListIcon("Postpaid", R.drawable.cms))

                adapter= MyBig9Adapter(iconList4,R.drawable.circle_shape2,object : CallBack{
                    override fun getValue(s: String) {
                        when(s){
                            getString(R.string.balance)->{
                                showBalencePopup(binding.root.context)
                            }
                        }

                    }

                })
            }*/

            recycleAEPS.apply {
                iconList5.clear()
                iconList5.add(ListIcon(getString(R.string.balance), R.drawable.transaction_history))
                iconList5.add(ListIcon(getString(R.string.cash_withdraw), R.drawable.cashcol))
                iconList5.add(ListIcon(getString(R.string.mini_statement), R.drawable.ministatement))

                iconList5.add(ListIcon(getString(R.string.aadhar_pay), R.drawable.aadharpay))

                adapter= AEPSAdapter(iconList5,R.drawable.circle_shape2,object : CallBack {
                    override fun getValue(s: String) {
                        serviceNavigation(s)
                       /* when(s) {
                            getString(R.string.balance) -> {
                                showBalencePopup(binding.root.context)
                            }

                            getString(R.string.cash_withdraw) -> {
                                isCashWithdraw=true
                                findNavController().navigate(R.id.action_homeFragment2_to_cashWithdrawFragment)
                            }


                            getString(R.string.mini_statement) -> {
                                viewModel.reportType.value=getString(R.string.dmt)
                                findNavController().navigate(R.id.action_homeFragment2_to_miniStatementFragment)
                               // findNavController().navigate(R.id.action_homeFragment2_to_cashWithdrawFragment)
                            }

                            getString(R.string.aadhar_pay) -> {
                                isCashWithdraw=false
                                findNavController().navigate(R.id.action_homeFragment2_to_cashWithdrawFragment)
                            }




                        }*/
                    }
                })

            }

            recycleViewEpayBanking.apply {
                iconList6.clear()
                iconList6.add(ListIcon(getString(R.string.move_to_bank), R.drawable.bank_transfer_icon))
                iconList6.add(ListIcon(getString(R.string.move_to_wallet), R.drawable.balance))
                iconList6.add(ListIcon(getString(R.string.ePotly), R.drawable.epotlyinb))
                iconList6.add(ListIcon(getString(R.string.payment_request), R.drawable.balance))
                //circle_shape
                adapter= BankingAdapter(iconList6,R.drawable.circle_shape2, object : CallBack {
                    override fun getValue(s: String) {
                        serviceNavigation(s)
                    }

                })
            }
            recycleTravel.apply {
                iconList7.clear()
                iconList7.add(ListIcon(getString(R.string.flight), R.drawable.ic_flight))
                iconList7.add(ListIcon(getString(R.string.train), R.drawable.ic_train))
                iconList7.add(ListIcon(getString(R.string.bus), R.drawable.bus))
                iconList7.add(ListIcon(getString(R.string.hotel), R.drawable.hotel))

                adapter= TravelAdapter(iconList7, R.drawable.circle_shape2, object : CallBack {
                    override fun getValue(s: String) {
                        serviceNavigation(s)
                    }

                })
            }


            recycleReport.apply {
                iconList8.clear()
                iconList8.add(ListIcon(getString(R.string.payment), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.transactions), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.dmt), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.load_Requests), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.wallet_ledger), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.cashout_ledger), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.aeps), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.micro_atm), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.commissions), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.bank_settle), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.wallet_settle), R.drawable.report))
                iconList8.add(ListIcon(getString(R.string.complaints), R.drawable.report))


                adapter= ReportAdapter(iconList8,R.drawable.circle_shape2,object : CallBack {
                    override fun getValue(s: String) {
                        viewModel.reportType.value=s//.replaceFirstChar(Char::titlecase)
                        findNavController().navigate(R.id.action_homeFragment2_to_reportFragment)
                       /*when(s){

                           getString(R.string.payment)->{}
                           getString(R.string.transactions)->{}
                           getString(R.string.dmt)->{}
                           getString(R.string.load_Requests)->{}
                           getString(R.string.wallet_ledger)->{}
                           getString(R.string.cashout_ledger)->{}
                           getString(R.string.aeps)->{}
                           getString(R.string.micro_atm)->{}
                           getString(R.string.commissions)->{}
                           getString(R.string.bank_settle)->{}
                           getString(R.string.wallet_settle)->{}
                           getString(R.string.complaints)->{}

                       }*/
                    }

                })
            }

            recycleAccount.apply {

                iconList9.clear()
                iconList9.add(ListIcon(getString(R.string.myaccount), R.drawable.myaccount))
                iconList9.add(ListIcon(getString(R.string.support), R.drawable.baseline_notifications_24))
                iconList9.add(ListIcon(getString(R.string.likeus), R.drawable.like_us))
                iconList9.add(ListIcon(getString(R.string.usage_terms), R.drawable.baseline_assignment_24))

                iconList9.add(ListIcon(getString(R.string.password), R.drawable.baseline_lock_person_24))
                iconList9.add(ListIcon(getString(R.string.certificate), R.drawable.baseline_receipt_24))
                iconList9.add(ListIcon(getString(R.string.logout), R.drawable.baseline_logout_24))


                adapter= ReportAdapter(iconList9,R.drawable.circle_shape2,object : CallBack {
                    override fun getValue(s: String) {
                        serviceNavigation(s)
                       /* when(s){
                           getString(R.string.myaccount)->{
                            findNavController().navigate(R.id.action_homeFragment2_to_userDetailsFragment)
                           }
                           getString(R.string.support)->{
                               findNavController().navigate(R.id.action_homeFragment2_to_supportFragment)
                           }
                           getString(R.string.likeus)->{}
                           getString(R.string.usage_terms)->{
                               findNavController().navigate(R.id.action_homeFragment2_to_termsAndConditionFragment)
                           }
                           getString(R.string.password)->{
                               findNavController().navigate(R.id.action_homeFragment2_to_changePasswordFragment)
                           }
                           getString(R.string.certificate)->{
                               findNavController().navigate(R.id.action_homeFragment2_to_certificateFragment)
                           }
                           getString(R.string.logout)->{
                               context?.let { ctx->
                                   ctx.userLogout()
                               }
                           }
                        }*/
                    }

                })
            }

        }
    }

    /*private fun rotateView(view: View, degrees: Float) {
        val rotation = ObjectAnimator.ofFloat(view, "rotation", degrees)
        rotation.duration = 500 // Adjust the duration as needed
        rotation.start()
    }*/

    fun init() {
        //sharedPreff.setTestData("Abcd")
        //Toast.makeText(requireActivity(), ""+sharedPreff.getTestData(), Toast.LENGTH_SHORT).show()
        iconList.add(ListIcon("Card", R.drawable.bb1))
        iconList.add(ListIcon("Card", R.drawable.b3))
        iconList.add(ListIcon("Card", R.drawable.card))
        iconList.add(ListIcon("Card", R.drawable.card2))
        iconList.add(ListIcon("Card", R.drawable.bb1))
        iconList.add(ListIcon("Card", R.drawable.card2))
        iconList.add(ListIcon("Card", R.drawable.card))
        iconList.add(ListIcon("Card", R.drawable.card2))
        binding.viewPager2.apply {
            val scaleMin = 0.32f // Minimum scale
            val scaleMax = 0.45f // Maximum scale
            setupCarousel(this, scaleMin, scaleMax)
            //offscreenPageLimit = 3
            /* setPageTransformer { page, position ->
                 page.translationX =
                     -position * (page.width / 3f)
             }*/
            adapter = BannerViewpagerAdapter(iconList)

            currentItem = 2


            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    // Check if the user scrolls to the first or last item, then perform looping.
                    if (position == 0) {
                        // Scroll to the last item.
                        // currentItem=adapter?.itemCount?.minus(1) ?: 0
                        // binding.viewPager2.setCurrentItem(adapter.getItemCount() - 1, false)
                    } else if (position == (adapter?.itemCount ?: 0)) {
                        // Scroll to the first item.
                        binding.viewPager2.setCurrentItem(0, false)
                    }
                }
            })

        }






        iconList2.add(ListIcon("Card", R.drawable.sa1))
        iconList2.add(ListIcon("Card", R.drawable.sa2))
        iconList2.add(ListIcon("Card", R.drawable.sa3))
        iconList2.add(ListIcon("Card", R.drawable.sa4))
        iconList2.add(ListIcon("Card", R.drawable.sa2))
        iconList2.add(ListIcon("Card", R.drawable.sa5))
        binding.viewPager3.apply {
            autoScrollHandler = AutoScrollHandler(this)
            adapter = BannerViewpagerAdapter(iconList2)
        }
        binding.tvMessage.isSelected = true

    }

    fun setupCarousel(
        viewPager: ViewPager2,
        minScale: Float = 0.35f,
        nextItemAlpha: Float = 0.5f
    ) {
        viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx =
            viewPager.context.resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            viewPager.context.resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position

            //get x-y ratio
            val ratio = page.scaleX / page.scaleY
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (minScale * Math.abs(position))
            // Next line scales the item's width. You can remove it if you don't want this effect
            /*if (!(ratio * page.scaleY).isNaN())
                page.scaleX = ratio * page.scaleY
            else
                page.scaleX = 1 - (minScale * abs(position))*/
            page.scaleX = 1 - (minScale * Math.abs(position))

            // If you want a fading effect uncomment the next line:
            page.alpha = nextItemAlpha + (1 - Math.abs(position))
            page.elevation = -Math.abs(position)
        }
        viewPager.setPageTransformer(pageTransformer)

        //// The ItemDecoration gives the current (centered) item horizontal margin so that
        //// it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            viewPager.context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager.addItemDecoration(itemDecoration)

    }

    override fun onResume() {
        super.onResume()
        if(isFromSearchPage){
            if (searchValue.isNotEmpty()){
                serviceNavigation(searchValue)
                searchValue=""
            }
            isFromSearchPage=false
        }
        autoScrollHandler.startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        autoScrollHandler.stopAutoScroll()
    }


}