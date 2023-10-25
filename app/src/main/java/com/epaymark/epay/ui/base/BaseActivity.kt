package com.epaymark.epay.ui.base

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epaymark.epay.ui.activity.NetworkActivity
import com.epaymark.epay.utils.common.MethodClass
import com.epaymark.epay.utils.helpers.NoNetworkReceiver
import com.epaymark.epay.utils.`interface`.NetworkConnectionListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseActivity: AppCompatActivity()/*, NetworkConnectionListener */{
   lateinit var nonetwork : NoNetworkReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nonetwork = NoNetworkReceiver(object :NetworkConnectionListener{
            override fun onNetworkConnection(connectionStatus: Boolean) {
                if (!MethodClass.check_networkconnection(this@BaseActivity))
                    startActivity(Intent(this@BaseActivity, NetworkActivity::class.java))

            }

        })
     }

    override fun onResume() {
        super.onResume()
       registerReceiver(nonetwork, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(nonetwork)
    }


    /* override fun onNetworkConnection(connectionStatus: Boolean) {
         if (!MethodClass.check_networkconnection(this))
             startActivity(Intent(this, NetworkActivity::class.java))
     }*/
}