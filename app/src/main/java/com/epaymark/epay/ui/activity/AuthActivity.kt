package com.epaymark.epay.ui.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.epaymark.epay.R
import com.epaymark.epay.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    lateinit var binding:ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the app to full-screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Hide the navigation bar and make the content layout full-screen
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_FULLSCREEN
                )
        //setContentView(R.layout.activity_auth)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        setdata()
        viewOnClick()

    }

    private fun viewOnClick() {
        binding.apply {
            btnConfirmLocation.setOnClickListener {
                binding.consLogin.visibility=View.GONE
                binding.consOtp.visibility=View.VISIBLE
                binding.imgLogo.visibility=View.VISIBLE
                binding.tvWelcometext.visibility=View.VISIBLE
            }
        }

    }

    private fun setdata() {

        //binding.animationView.setAnimationFromUrl(Constants.LOTTIE_CONFETTIE_LINK)
        //        lottie_anim.setSpeed(0.7f);
        binding.animationView.apply {
        setAnimation(R.raw.bg_login)

        playAnimation()



            addAnimatorUpdateListener { animation ->
                var duration:Float=animation.animatedFraction
                if (duration>0.5f){
                    binding.consLogin.visibility=View.VISIBLE
                    binding.imgLogo.visibility=View.VISIBLE
                }
            }


            addAnimatorListener(
            object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }
                override fun onAnimationEnd(animation: Animator) {
                    lifecycleScope.launch {
                       // binding.imgLogo.visibility=View.GONE
                        //binding.consLogin.visibility=View.VISIBLE
                        visibility=View.GONE
                        binding.constLayout.setBackgroundResource(R.color.gray)
                        /*delay(500L)
                        startActivity(
                            Intent(
                                this@CongratulationsActivity,
                                DashBoardActivity::class.java
                            )
                        )
                        finish()*/
                        /*ObjectAnimator.ofFloat(binding.imgLogo, "translationY", 100f).apply {
                            duration = 2000
                            start()
                        }*/

                        setSecondAnimation()

                    }
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
    }
    }

    private fun setSecondAnimation() {
        binding.animationViewHeader.apply {
            setAnimation(R.raw.login_header_bg)
            playAnimation()

            addAnimatorListener(
                object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {

                    }
                    override fun onAnimationEnd(animation: Animator) {
                        lifecycleScope.launch {
                            // binding.imgLogo.visibility=View.GONE
                            //binding.consLogin.visibility=View.VISIBLE
                            visibility=View.GONE
                            binding.constLayout.setBackgroundResource(R.color.gray)
                            /*delay(500L)
                            startActivity(
                                Intent(
                                    this@CongratulationsActivity,
                                    DashBoardActivity::class.java
                                )
                            )
                            finish()*/
                            binding.tvWelcometext.visibility=View.VISIBLE
                            ObjectAnimator.ofFloat(binding.imgLogo, "translationY", 100f).apply {
                                duration = 2000
                                start()
                            }



                        }
                    }

                    override fun onAnimationCancel(animation: Animator) {}
                    override fun onAnimationRepeat(animation: Animator) {}
                })
        }
    }
}