package com.epaymark.epay.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.ReportDetailsAdapter
import com.epaymark.epay.adapter.UserDetailsAdapter
import com.epaymark.epay.data.model.ReportModel
import com.epaymark.epay.data.model.Reportdetails
import com.epaymark.epay.data.model.UserDetails
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentReportDetailsBinding
import com.epaymark.epay.databinding.FragmentUserDetailsBinding
import com.epaymark.epay.ui.base.BaseFragment
import org.json.JSONObject

class UserDetailsFragment : BaseFragment() {
    lateinit var binding: FragmentUserDetailsBinding
    private val viewModel: MyViewModel by activityViewModels()
    var userDetailsList = ArrayList<UserDetails>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_details, container, false)
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
        binding.recycleViewUserdetails.apply {
            userDetailsList.add(UserDetails("Name","Test User"))
            userDetailsList.add(UserDetails("Business Name","Test Business Name"))
            userDetailsList.add(UserDetails("Registered Mobile Number","9999999999"))
            userDetailsList.add(UserDetails("Registered Email Id","test@test.com"))
            userDetailsList.add(UserDetails("Address","123, Park Street,Kolkata - 700001,West Bengal, India"))
            userDetailsList.add(UserDetails("District/City","Kolkata"))
            userDetailsList.add(UserDetails("State","West Bengal"))
            userDetailsList.add(UserDetails("Account Type","Distributor"))
            userDetailsList.add(UserDetails("Super Distributor","9999999999"))
            userDetailsList.add(UserDetails("Distributor","9999999999"))
            adapter= UserDetailsAdapter(userDetailsList)
        }
    }

    fun setObserver() {
        binding.apply {

        }

    }
}