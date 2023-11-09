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
                            reportList.add(ReportModel("001","778.00","10-10-2023","Payment send",0, desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",imageInt = R.drawable.send_logo))
                            reportList.add(ReportModel("002","778.00","10-10-2023","Payment received",1 ,desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",imageInt = R.drawable.receive_logo))


                        }


                        getString(R.string.transactions) -> {
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "778.00",
                                    "10-10-2023",
                                    "Failed",
                                    0,
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
                                    1,
                                    desc = "AEPS-MINI_STATEMENT -9163265863\nReferance id - 30000018",
                                    imageInt = R.drawable.right_tick
                                )
                            )

                        }

                        getString(R.string.dmt) -> {
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "778.00",
                                    "10-10-2023",
                                    "Refunded",
                                    0,
                                    desc = "Rajiv\nA/c No.:111111111111\nSender: 5555555555",
                                    imageInt = R.drawable.imps_logo,
                                    image1 = 2
                                )
                            )
                            reportList.add(
                                ReportModel(
                                    "002",
                                    "778.00",
                                    "10-10-2023",
                                    getString(R.string.success),
                                    1,
                                    desc = "Jhuma Chowdhary\nA/c No.:000000000000\nSender :8888888888",
                                    imageInt = R.drawable.imps_logo,
                                    image1 = 2
                                )
                            )

                        }

                        getString(R.string.load_Requests) -> {
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "778.00",
                                    "10-10-2023",
                                    "Credit/Sales Supports",
                                    2,
                                    desc = "Axis Bank-Online\nPayment Ref id- 5376254\nApproved on 2023-10-30",
                                    imageInt = R.drawable.right_tick
                                )
                            )
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "778.00",
                                    "10-10-2023",
                                    "Credit/Sales Supports",
                                    2,
                                    desc = "Axis Bank-Online\nSame Bank\nPayment Ref Id: ASEESSS",
                                    imageInt = R.drawable.rounded_i
                                )
                            )
                        }

                        getString(R.string.wallet_ledger) -> {
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "778.00",
                                    "10-10-2023",
                                    "ePotlyNB Money\nForward",
                                    2,
                                    desc = "",
                                    price2 = "Closing ₹1021.00",
                                    proce1TextColor = 1
                                )
                            )
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "-778.00",
                                    "10-10-2023",
                                    "ePotlyNB Money\nForward",
                                    2,
                                    desc = "",
                                    price2 = "Closing ₹1021.00",
                                    proce1TextColor = 2
                                )
                            )

                        }

                        getString(R.string.cashout_ledger) -> {
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "778.00",
                                    "10-10-2023",
                                    "Wallet Statement",
                                    2,
                                    desc = "",
                                    price2 = "Closing ₹1021.00",
                                    proce1TextColor = 1
                                )
                            )
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
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "778.00",
                                    "10-10-2023",
                                    "Failed",
                                    0,
                                    desc = "Type: Settle to bank",

                                    image1 = 3
                                )
                            )
                        }

                        getString(R.string.wallet_settle) -> {
                            reportList.add(
                                ReportModel(
                                    "001",
                                    "10.00",
                                    "10-10-2023",
                                    "Failed",
                                    0,
                                    desc = "Type: Settle to wallet\nstatus - processed\ndetails-wallet",

                                    image1 = 3
                                )
                            )
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