package com.epaymark.epay.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.epaymark.epay.R
import com.epaymark.epay.adapter.reportAdapter.ReportAdapter
import com.epaymark.epay.data.model.ReportModel
import com.epaymark.epay.data.model.ReportPropertyModel
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentReportBinding
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.CallBack

class ReportFragment : BaseFragment() {
    lateinit var binding: FragmentReportBinding
    private val viewModel: MyViewModel by activityViewModels()
    var reportList = ArrayList<ReportModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false)
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

          tvStartDate.setOnClickListener {
                it.showDatePickerDialog(object : CallBack {
                    override fun getValue(s: String) {
                        viewModel?.startDate?.value=s
                    }

                })
            }

            tvEndDate.setOnClickListener {
                it.showDatePickerDialog(object : CallBack {
                    override fun getValue(s: String) {
                        viewModel?.enddate?.value=s
                    }

                })
            }

            recycleViewReport.apply {
                reportList.clear()

                viewModel?.reportType?.value?.let { type ->
                    when (type) {

                        getString(R.string.payment) -> {
                            reportList.add(ReportModel("001","778.00","10-10-2023","Payment send",false, desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",imageInt = R.drawable.send_logo))
                            reportList.add(ReportModel("002","778.00","10-10-2023","Payment received",true ,desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",imageInt = R.drawable.receive_logo))


                        }


                        getString(R.string.transactions) -> {
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "778.00",
                                    "10-10-2023",
                                    "Failed",
                                    false,
                                    desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",
                                    imageInt = R.drawable.close_icon
                                )
                            )
                            reportList.add(
                                ReportModel(
                                    "002",
                                    "778.00",
                                    "10-10-2023",
                                    getString(R.string.success),
                                    true,
                                    desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",
                                    imageInt = R.drawable.right_tick
                                )
                            )

                        }

                        getString(R.string.dmt) -> {
                            ReportPropertyModel("Transaction id")
                            reportList.add(ReportModel("001","778.00","10-10-2023","Refunded",false, desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",imageInt = R.drawable.imps_logo))
                            reportList.add(ReportModel("002","778.00","10-10-2023","Success",true ,desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",imageInt = R.drawable.imps_logo))


                        }

                        getString(R.string.load_Requests) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        getString(R.string.wallet_ledger) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        getString(R.string.cashout_ledger) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        getString(R.string.aeps) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        getString(R.string.micro_atm) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        getString(R.string.commissions) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        getString(R.string.bank_settle) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        getString(R.string.wallet_settle) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        getString(R.string.complaints) -> {
                            ReportPropertyModel("Transaction id")
                        }

                        else -> {}
                    }
                }




                viewModel?.reportType?.value?.let {type->

                    val reportPropertyModel=   when(type){

                        getString(R.string.payment)->{
                            ReportPropertyModel("Transaction id")
                        }
                        getString(R.string.transactions)->{ReportPropertyModel("Transaction id","")}
                        getString(R.string.dmt)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.load_Requests)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.wallet_ledger)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.cashout_ledger)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.aeps)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.micro_atm)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.commissions)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.bank_settle)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.wallet_settle)->{ReportPropertyModel("Transaction id")}
                        getString(R.string.complaints)->{ReportPropertyModel("Transaction id")}

                        else -> {ReportPropertyModel("Transaction id")}
                    }


                    adapter= ReportAdapter(reportPropertyModel,reportList,  object : CallBack {
                        override fun getValue(s: String) {

                        }

                    })
                }

            }
        }
    }

    fun initView() {
        viewModel?.apply {
            startDate.value ="".currentdate()
            enddate.value="".currentdate()
        }

    }

    fun setObserver() {
        binding.apply {

        }

    }
}