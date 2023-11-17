package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.AccountDetailsAdapter
import com.epaymark.epay.data.model.AccountDetailsModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentMoveToBankBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.CallBack


class MoveToBankFragment : BaseFragment() {
    lateinit var binding: FragmentMoveToBankBinding
    private val viewModel: MyViewModel by activityViewModels()
    var accountDetailsList = ArrayList<AccountDetailsModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_move_to_bank, container, false)
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
        binding.recycleViewBankDetails.apply {

            accountDetailsList.add(AccountDetailsModel("Test User",true,"0087200100008770","PUNB00389600"))
            accountDetailsList.add(AccountDetailsModel("Test User",false,"0087200100008770","PUNB00389600"))
            accountDetailsList.add(AccountDetailsModel("Test User",true,"0087200100008770","PUNB00389600"))
            accountDetailsList.add(AccountDetailsModel("Test User",true,"0087200100008770","PUNB00389600"))
            accountDetailsList.add(AccountDetailsModel("Test User",true,"0087200100008770","PUNB00389600"))
            accountDetailsList.add(AccountDetailsModel("Test User",true,"0087200100008770","PUNB00389600"))
            accountDetailsList.add(AccountDetailsModel("Test User",true,"0087200100008770","PUNB00389600"))
            accountDetailsList.add(AccountDetailsModel("Test User",true,"0087200100008770","PUNB00389600"))
            adapter= AccountDetailsAdapter(accountDetailsList,object:CallBack{
                override fun getValue(s: String) {
                    activity?.let {act->
                        val moveToBankBottomSheetDialog = MoveToBankBottomSheetDialog(object : CallBack {
                            override fun getValue(s: String) {

                                    val tpinBottomSheetDialog = TpinBottomSheetDialog(object : CallBack {
                                        override fun getValue(s: String) {
                                            Toast.makeText(requireActivity(), "$s", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                    tpinBottomSheetDialog.show(
                                        act.supportFragmentManager,
                                        tpinBottomSheetDialog.tag
                                    )


                            }
                        })
                        moveToBankBottomSheetDialog.show(
                            act.supportFragmentManager,
                            moveToBankBottomSheetDialog.tag
                        )
                    }

                }

            })
        }
    }

    fun setObserver() {
        binding.apply {

        }

    }


}