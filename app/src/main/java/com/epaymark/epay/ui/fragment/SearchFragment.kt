package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.epaymark.epay.R
import com.epaymark.epay.adapter.ReportAdapter
import com.epaymark.epay.adapter.SearchAdapter
import com.epaymark.epay.data.model.ListIcon
import com.epaymark.epay.databinding.FragmentSearchBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.common.MethodClass.userLogout
import com.epaymark.epay.utils.helpers.Constants
import com.epaymark.epay.utils.helpers.Constants.isFromSearchPage
import com.epaymark.epay.utils.helpers.Constants.searchList
import com.epaymark.epay.utils.helpers.Constants.searchValue
import com.epaymark.epay.utils.helpers.Constants.utilityValue
import com.epaymark.epay.utils.`interface`.CallBack

class SearchFragment : BaseFragment() {
    lateinit var binding: FragmentSearchBinding
    var searchAdapter:SearchAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        isFromSearchPage=true
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObserver()
    }

    fun initView() {
        binding?.apply {
            recycleViewSearchService?.apply {
                searchAdapter= SearchAdapter(searchList,R.drawable.circle_shape2,object : CallBack {
                override fun getValue(s: String) {
                    utilityValue=s
                    findNavController().popBackStack()
                }

            })
           adapter=searchAdapter
        }
        }

    }

    fun setObserver() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length>1){
                    binding.recycleViewSearchService.visibility=View.VISIBLE
                    searchAdapter?.filteredList?.size?.let {size->
                        if (size >0){
                            binding.tvNoDataFound.isVisible=false
                        }
                        else{
                            binding.tvNoDataFound.text="No data found"
                            binding.tvNoDataFound.isVisible=true
                        }
                    }

                }
                else{

                    binding.recycleViewSearchService.visibility=View.GONE
                    binding.tvNoDataFound.isVisible=true
                    binding.tvNoDataFound.text="Search..."
                }

                //binding.tvNoDataFound.isVisible=!binding.recycleViewSearchService.isVisible

                searchAdapter?.filter?.filter(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}