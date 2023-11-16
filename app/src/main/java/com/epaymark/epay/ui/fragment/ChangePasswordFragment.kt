package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.ViewPager2Adapter
import com.epaymark.epay.adapter.YourFragmentPagerAdapter
import com.epaymark.epay.data.model.ContactModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentChangePasswordBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.ui.fragment.tablayout.ChangeLoginPinFragment
import com.epaymark.epay.ui.fragment.tablayout.ChangeTPINPinFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ChangePasswordFragment : BaseFragment() {
    lateinit var binding: FragmentChangePasswordBinding
    private val viewModel: MyViewModel by activityViewModels()
    var contactModelList = ArrayList<ContactModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password, container, false)
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
        }
        }


    fun initView() {
        binding.viewPager.apply {

             adapter =  YourFragmentPagerAdapter(childFragmentManager)
            binding.tabLayout.setupWithViewPager(this)
            // adapter = MyAdapter(this.context, arrayListOf("Change Login PIN","Change TPIN","Reset TPIN"))//ViewPagerAdapter(requireActivity())


           /* TabLayoutMediator(binding.tabLayout, this) { tab, position ->
                tab.text = adapter.getPageTitle(position)
            }.attach()*/




            /*var adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
            TabLayoutMediator(binding.tabLayout, this) { tab, position ->
                tab.text = adapter.getPageTitle(position)
            }.attach()

            adapter = adapter*/
        }

    }

    fun setObserver() {
        binding.apply {

        }

    }


}