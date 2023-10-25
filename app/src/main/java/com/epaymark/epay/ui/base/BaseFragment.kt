package com.epaymark.epay.ui.base

import androidx.fragment.app.Fragment
import com.epaymark.epay.utils.helpers.SharedPreff
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment: Fragment(){
    @Inject
    lateinit var sharedPreff: SharedPreff
}