package com.epaymark.epay.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.adapter.ElectricPriceListAdapter
import com.epaymark.epay.data.model.ElectricListModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.ElectricPriceListFragmentBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.CallBack

class ElectricPriceListFragment() : BaseFragment() {
    lateinit var binding: ElectricPriceListFragmentBinding
    private val viewModel: MyViewModel by activityViewModels()

    var electricList = ArrayList<ElectricListModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.electric_price_list_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    /*override fun getTheme(): Int {
        return R.style.SheetDialog
    }*/
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
        onViewClick()
    }

    private fun onViewClick() {
        binding.apply {
            binding.apply {
                imgBack.back()
            }
            tvProcidToPay.setOnClickListener {
                viewModel?.consumerIdPrice?.value?.let {
                    if (it.isNotEmpty()) {
                        findNavController().popBackStack()
                    }
                    else{
                        Toast.makeText(tvProcidToPay.context, "Please select price", Toast.LENGTH_SHORT).show()
                    }
                    }
            }
        }

    }

    private fun setObserver() {

    }

    private fun initView() {

        binding.recycleOperator.apply {

            electricList.add(ElectricListModel("770","Nov 2023","1 month",false));
            electricList.add(ElectricListModel("770","Dec 2023","2 month",false));
            electricList.add(ElectricListModel("770","Jan 2024","3 month",false));





            adapter= ElectricPriceListAdapter(electricList, object : CallBack {
                override fun getValue(s: String) {
                    viewModel?.apply {
                        viewModel?.consumerIdPrice?.value=s
                      //  findNavController().popBackStack()
                    }

                    // callBack.getValue(s)
                    //findNavController().popBackStack()
                }

            })
        }
    }

}