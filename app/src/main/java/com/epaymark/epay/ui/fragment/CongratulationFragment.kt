package com.epaymark.epay.ui.fragment

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.epaymark.epay.R
import com.epaymark.epay.databinding.FragmentCongratulationBinding
import com.epaymark.epay.ui.activity.AuthenticationActivity
import com.epaymark.epay.ui.activity.DashboardActivity
import com.epaymark.epay.utils.helpers.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CongratulationFragment : Fragment() {
    lateinit var binding:FragmentCongratulationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_congratulation, container, false)

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initView()
        // setObserver()
        setdata()
    }
    private fun setdata() {
        binding.lottieTickAnim.setAnimationFromUrl(Constants.LOTTIE_TICK_LINK)
        binding.lottieTickAnim.playAnimation()
        binding.lottieConfettiAnim.setAnimationFromUrl(Constants.LOTTIE_CONFETTIE_LINK)
        //        lottie_anim.setSpeed(0.7f);
        binding.lottieConfettiAnim.playAnimation()
        binding.lottieConfettiAnim.addAnimatorListener(
            object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    lifecycleScope.launch {
                        delay(500L)

                        (activity as? AuthenticationActivity)?.let{act->
                            startActivity(Intent(act, DashboardActivity::class.java))
                            act.finish()
                        }
                    }
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
    }


}