package com.epaymark.epay.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.epaymark.epay.R
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.ActivityDashboardBinding
import com.epaymark.epay.network.ResponseState
import com.epaymark.epay.network.RetrofitHelper.handleApiError
import com.epaymark.epay.ui.base.BaseActivity
import com.epaymark.epay.ui.popup.ErrorPopUp
import com.epaymark.epay.ui.popup.LoadingPopup
import com.epaymark.epay.utils.helpers.Constants.isAfterReg
import com.epaymark.epay.utils.helpers.Constants.isRecept
import com.epaymark.epay.utils.helpers.RequestBodyHelper
import com.epaymark.epay.utils.helpers.ScreenshotUtils.Companion.takeScreenshot
import com.epaymark.epay.utils.helpers.SharedPreff
import com.epaymark.epay.utils.helpers.helper.decryptData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity  : BaseActivity() {
    lateinit var binding:ActivityDashboardBinding
    private lateinit var myViewModel: MyViewModel
    private var navController: NavController? = null
    @Inject
    lateinit var requestBodyHelper: RequestBodyHelper
    private val REQUEST_MEDIA_PROJECTION=1
    @Inject
    lateinit var sharedPreff: SharedPreff

    var loadingPopup: LoadingPopup? = null
    var errorPopUp: ErrorPopUp? = null
    private val mTag = "tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        init()
        observer()

        loadingPopup = LoadingPopup(this)
        errorPopUp = ErrorPopUp(this)

       // myViewModel.login(requestBodyHelper.getLoginRequest("test@abc.com", "123"))

    }

    fun init(){

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        intent?.let {intentData->
            val isReceptBooleanValue=intentData.getBooleanExtra(isRecept,false)
            val isAfterRegVal=intentData.getBooleanExtra(isAfterReg,false)
            if (isReceptBooleanValue){

            }
            if (isAfterRegVal){
                navController?.navigate(R.id.homeFragment2)
            }
        }

        var currentFragmentId = navController?.currentDestination?.id
        if (currentFragmentId==R.id.homeFragment){
            binding.bottomNav.visibility= View.VISIBLE
            binding.clHeader.visibility= View.VISIBLE
        }
        else{
            binding.bottomNav.visibility= View.GONE
            binding.clHeader.visibility= View.GONE
        }
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

    fun navigate(){
        navController?.navigate(R.id.homeFragment2)
    }

    fun shareImage(screenshotBitmap: Bitmap) {
        takeScreenshot(this,screenshotBitmap)
       /* val mediaProjectionManager =
            getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startActivityForResult(
            mediaProjectionManager.createScreenCaptureIntent(),
            REQUEST_MEDIA_PROJECTION
        )*/
    }



}