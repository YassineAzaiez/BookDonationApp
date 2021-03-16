package com.example.booksdonationapp.presentation

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.ActivityMainBinding
import com.example.booksdonationapp.presentation.utils.gone
import com.example.booksdonationapp.presentation.commun.BaseActivity
import com.example.booksdonationapp.presentation.utils.hideSystemUI

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun getLayoutId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }


    @Suppress("DEPRECATION")
    override fun initView() {

        binding.bottomNavigationView.background = null
    }

    override fun initData() {

    }


    fun hideBottomNavigation() {
        binding.bottomNavigationView.gone()
        binding.bottomAppBar.gone()
        binding.fab.gone()
    }
}