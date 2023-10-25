package com.epaymark.epay.utils.helpers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.epaymark.epay.utils.common.MethodClass
import com.epaymark.epay.utils.`interface`.NetworkConnectionListener


class NoNetworkReceiver(private val ntwrklstnr : NetworkConnectionListener?) : BroadcastReceiver(){
    override fun onReceive(ctx: Context?, p1: Intent?) {
        ctx?.let {
            if (!MethodClass.check_networkconnection(it)){
                ntwrklstnr?.onNetworkConnection(false)
            }else{
                ntwrklstnr?.onNetworkConnection(true)
            }
        }

    }


}