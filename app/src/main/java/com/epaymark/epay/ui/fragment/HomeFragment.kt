package com.epaymark.epay.ui.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
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
import com.epaymark.epay.adapter.MyBig9Adapter
import com.epaymark.epay.adapter.RechargeAdapter
import com.epaymark.epay.adapter.ReportAdapter
import com.epaymark.epay.adapter.TravelAdapter
import com.epaymark.epay.adapter.UPIAdapter
import com.epaymark.epay.data.model.ListIcon
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentHomeBinding


import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.popup.CustomPopup.showBalencePopup
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
    lateinit var binding: FragmentHomeBinding
    private lateinit var autoScrollHandler: AutoScrollHandler
    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this*/

        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        init()
        viewOnClick()
        return view
    }

    private fun viewOnClick() {
        binding.apply {
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



            arrowImageView2.setOnClickListener {

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




            arrowImageViewNew2.setOnClickListener {

                if (isRotated3) {

                    rotateView(arrowImageViewNew2, 0f)


                    val layoutParams = recycleUPI.layoutParams
                    layoutParams.height = 40
                    recycleUPI.layoutParams = layoutParams
                    recycleUPI.startAnimation(collapseAnimation)

                    recycleUPI.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageViewNew2, 180f)
                    val layoutParams = recycleUPI.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    recycleUPI.layoutParams = layoutParams
                    recycleUPI.visibility = View.VISIBLE
                    recycleUPI.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated3 = !isRotated3
            }


            arrowImageViewNew2.setOnClickListener {

                if (isRotated3) {

                    rotateView(arrowImageViewNew2, 0f)


                    val layoutParams = recycleUPI.layoutParams
                    layoutParams.height = 40
                    recycleUPI.layoutParams = layoutParams
                    recycleUPI.startAnimation(collapseAnimation)

                    recycleUPI.visibility = View.INVISIBLE

                } else {
                    rotateView(arrowImageViewNew2, 180f)
                    val layoutParams = recycleUPI.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    recycleUPI.layoutParams = layoutParams
                    recycleUPI.visibility = View.VISIBLE
                    recycleUPI.startAnimation(
                        AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.expand_animation
                        )
                    )

                }

                isRotated3 = !isRotated3
            }

            arrowImageViewNew3.setOnClickListener {

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
            }


            arrowImageViewNew4.setOnClickListener {

                if (isRotated3) {

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

                isRotated3 = !isRotated3
            }


            recycleViewRecharge.apply {
                iconList3.clear()
                iconList3.add(ListIcon(getString(R.string.mobile_recharge), R.drawable.db_mobile))
                iconList3.add(ListIcon(getString(R.string.dth_recharge), R.drawable.ic_dth_recharge))
                iconList3.add(ListIcon(getString(R.string.electric), R.drawable.electric))
                iconList3.add(ListIcon("Fast Tag", R.drawable.icons8_fastag))
                iconList3.add(ListIcon("Google Play", R.drawable.google_play))
                iconList3.add(ListIcon("Insurance", R.drawable.insurance))
                iconList3.add(ListIcon("Water", R.drawable.water))
                iconList3.add(ListIcon("View More", R.drawable.view_more))
                adapter= RechargeAdapter(iconList3,R.drawable.circle_shape, object : CallBack {
                    override fun getValue(s: String) {
                        Log.d("TAG_mobile_recharge", "getValue: $s")
                        when(s){
                            getString(R.string.mobile_recharge)->{
                                findNavController().navigate(R.id.action_homeFragment2_to_mobileRechargeFragment)
                            }
                            getString(R.string.dth_recharge)->{
                                findNavController().navigate(R.id.action_homeFragment2_to_DTHRechargeFragment)
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

                })
            }

            recycleMyBig.apply {
                iconList4.clear()
                iconList4.add(ListIcon("Balence", R.drawable.transaction_history))
                iconList4.add(ListIcon("CMS", R.drawable.cms))
                iconList4.add(ListIcon("Wallet", R.drawable.db_balance))
                iconList4.add(ListIcon("Postpaid", R.drawable.cms))

                adapter= MyBig9Adapter(iconList4,R.drawable.circle_shape2,object : CallBack{
                    override fun getValue(s: String) {
                        showBalencePopup(binding.root.context)
                    }

                })
            }

            recycleAEPS.apply {
                iconList5.clear()
                iconList5.add(ListIcon("AePS", R.drawable.ic_fingerprint_dark))
                iconList5.add(ListIcon("DMT", R.drawable.bank_transfer))
                iconList5.add(ListIcon("MATM", R.drawable.transaction_history))
                iconList5.add(ListIcon("Settlement", R.drawable.sattlement))

                adapter= AEPSAdapter(iconList5,R.drawable.circle_shape2)
            }

            recycleUPI.apply {
                iconList6.clear()
                iconList6.add(ListIcon("Scan & Pay", R.drawable.baseline_qr_code_scanner_24))
                iconList6.add(ListIcon("To Contact", R.drawable.ic_contacts))
                iconList6.add(ListIcon("To Self", R.drawable.ic_self))
                iconList6.add(ListIcon("To Bank A/C", R.drawable.ic_bank))

                adapter= UPIAdapter(iconList6,R.drawable.circle_shape2)
            }
            recycleTravel.apply {
                iconList7.clear()
                iconList7.add(ListIcon(getString(R.string.flight), R.drawable.ic_flight))
                iconList7.add(ListIcon(getString(R.string.train), R.drawable.ic_train))
                iconList7.add(ListIcon(getString(R.string.bus), R.drawable.bus))
                iconList7.add(ListIcon(getString(R.string.hotel), R.drawable.hotel))

                adapter= TravelAdapter(iconList7, R.drawable.circle_shape2, object : CallBack {
                    override fun getValue(s: String) {

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



        }
    }

    private fun rotateView(view: View, degrees: Float) {
        val rotation = ObjectAnimator.ofFloat(view, "rotation", degrees)
        rotation.duration = 500 // Adjust the duration as needed
        rotation.start()
    }

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
        autoScrollHandler.startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        autoScrollHandler.stopAutoScroll()
    }


}