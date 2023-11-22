package com.epaymark.epay.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.epaymark.epay.R
import com.epaymark.epay.adapter.PhonePadAdapter
import com.epaymark.epay.adapter.PhonePadAdapter2
import com.epaymark.epay.data.viewMovel.MyViewModel
import com.epaymark.epay.databinding.FragmentLoginBinding
import com.epaymark.epay.databinding.FragmentLoginPinBinding
import com.epaymark.epay.ui.activity.RegActivity
import com.epaymark.epay.ui.base.BaseFragment
import com.epaymark.epay.utils.`interface`.KeyPadOnClickListner

class LoginPinfragment : BaseFragment() {
    lateinit var binding: FragmentLoginPinBinding
    var keyPad = ArrayList<Int>()
    private val myViewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_pin, container, false)
        binding.viewModel = myViewModel
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
            tvSwitchAcc.setOnClickListener {
                activity?.let {act->
                    val intent = Intent(act, RegActivity::class.java)
                    intent.putExtra("isForgotPin",true)
                    startActivity(intent)
                    act.finish()
                }

            }
        }

    }

    override fun onResume() {
        super.onResume()

    }
    fun initView() {
        setKeyPad(binding.recyclePhonePad)
        binding.apply {
            recyclePhonePad.requestFocus()
            hideKeyBoard(firstPinView)
        }

    }

    fun setObserver() {

    }

    fun setKeyPad(PhonePad: RecyclerView) {
        keyPad.clear()
        keyPad.add(1)
        keyPad.add(2)
        keyPad.add(3)
        keyPad.add(4)
        keyPad.add(5)
        keyPad.add(6)
        keyPad.add(7)
        keyPad.add(8)
        keyPad.add(9)
        keyPad.add(10)
        keyPad.add(0)
        keyPad.add(11)
        PhonePad.apply {

            adapter= PhonePadAdapter2(keyPad,object : KeyPadOnClickListner {
                override fun onClick(item: Int) {
                    //myViewModel.loginPin.value?.let {
                        if (item<=9 ) {
                            if (myViewModel.loginPin.value?.length!=6) {
                                val loginPin="${myViewModel.loginPin.value}$item"
                                myViewModel.loginPin.value= loginPin
                                if(myViewModel.loginPin.value?.length==6){
                                    if (myViewModel.loginPin.value=="123456") {
                                        findNavController().navigate(R.id.action_loginPinfragment_to_homeFragment2)
                                    }
                                }

                            }
                        }

                        else {
                            if (myViewModel.loginPin.value?.isNotEmpty() == true) {
                                myViewModel.loginPin.value = this.toString().substring(0,
                                    myViewModel.loginPin.value?.length?.minus(1) ?: 0
                                )
                            }

                        }
                    //}
                }

            })
            isNestedScrollingEnabled=false
        }
    }
}