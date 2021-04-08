package com.example.booksdonationapp.presentation.ui.launcher


import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.ActivitySplashBinding
import com.example.booksdonationapp.presentation.MainActivity
import com.example.booksdonationapp.presentation.utils.hideSystemUI
import com.example.booksdonationapp.presentation.utils.startActivity
import com.example.gamehub.commen.ui.BaseVmActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : BaseVmActivity<SplashViewModel>(SplashViewModel::class.java) {


    private val SPLASH_TIME_OUT_SPLACH: Long = 4000
    private lateinit var binding: ActivitySplashBinding
    private lateinit var fadeAnimation: Animation

    override fun startObserve() {

    }

    override fun getLayoutId(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {

    }

    override fun initView() {
        hideSystemUI()
        fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        binding.splashIcon.startAnimation(fadeAnimation)
        CoroutineScope(Dispatchers.Main).launch {
            binding.dotProgressBar.startAnimation()
            delay(SPLASH_TIME_OUT_SPLACH)
            startActivity<MainActivity> {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }


        }
    }
}