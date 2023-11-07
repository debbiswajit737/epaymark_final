package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.BillerListAdapter
import com.epaymark.epay.adapter.StateListAdapter
import com.epaymark.epay.data.model.StateCityModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentElectricRechargeBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.CallBack

class ElectricRechargeFragment : BaseFragment() {
    lateinit var binding: FragmentElectricRechargeBinding
    private val viewModel: MyViewModel by activityViewModels()
    var billerList = ArrayList<StateCityModel>()
    var billerListAdapter: BillerListAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_electric_recharge, container, false)
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


    }

    fun initView() {
        binding.apply {
            billerList.add(StateCityModel(false,"Kolkata"))
            billerList.add(StateCityModel(false,"Asansol"))
            billerList.add(StateCityModel(false,"Siliguri"))
            billerList.add(StateCityModel(false,"Kolkata"))
            billerList.add(StateCityModel(false,"Asansol"))
            billerList.add(StateCityModel(false,"Siliguri"))
            billerList.add(StateCityModel(false,"Kolkata"))
            billerList.add(StateCityModel(false,"Asansol"))
            billerList.add(StateCityModel(false,"Siliguri"))



           /* binding.recycleCity.apply {

                billerListAdapter= BillerListAdapter(billerList,object : CallBack {
                    override fun getValue(s: String) {



                    }

                })
                adapter=billerListAdapter


                binding.etSearch.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        billerListAdapter?.filter?.filter(s)
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }
                })
            }*/
        }

    }

    fun setObserver() {

    }


}