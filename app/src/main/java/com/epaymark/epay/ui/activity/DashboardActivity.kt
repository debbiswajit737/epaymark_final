package com.epaymark.epay.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.ActivityDashboardBinding
import com.epaymark.epay.network.ResponseState
import com.epaymark.epay.network.RetrofitHelper.handleApiError
import com.epaymark.epay.ui.base.BaseActivity
import com.epaymark.epay.ui.popup.ErrorPopUp
import com.epaymark.epay.ui.popup.LoadingPopup
import com.epaymark.epay.utils.helpers.RequestBodyHelper
import com.epaymark.epay.utils.helpers.SharedPreff
import com.epaymark.epay.utils.helpers.helper.decryptData
import com.epaymark.epay.utils.helpers.helper.encryptData
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity  : BaseActivity() {
    lateinit var activityDashboardBinding:ActivityDashboardBinding
    private lateinit var myViewModel: MyViewModel

    @Inject
    lateinit var requestBodyHelper: RequestBodyHelper

    @Inject
    lateinit var sharedPreff: SharedPreff

    var loadingPopup: LoadingPopup? = null
    var errorPopUp: ErrorPopUp? = null
    private val mTag = "tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        observer()

        loadingPopup = LoadingPopup(this)
        errorPopUp = ErrorPopUp(this)

        myViewModel.login(requestBodyHelper.getLoginRequest("test@abc.com", "123"))

    }


    fun observer(){

        myViewModel.loginResponseLiveData.observe(this) {

            when (it) {
                is ResponseState.Loading -> {
                    //loadingPopup?.show()
                }
                is ResponseState.Success -> {
                    loadingPopup?.dismiss()
                    Toast.makeText(this, ""+it?.data?.response?.data?.get(0)?.name, Toast.LENGTH_SHORT).show()
                    //var a=it.data?.response?.data?.get(0)?.name?.encryptData("ttt")
                    var a=it?.data?.response?.data?.get(0)?.name
                    var b=a?.decryptData("ttt")
                    Toast.makeText(this, "$b", Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Error -> {
                    loadingPopup?.dismiss()
                    handleApiError(it.isNetworkError,it.errorCode, it.errorMessage)
                }
            }
        }
    }
}