package com.example.gamehub.commen.ui

import android.os.Bundle
import com.example.booksdonationapp.presentation.commun.BaseActivity


abstract class BaseVmActivity<VM : BaseViewModel>(private val modelClass: Class<VM>) :
    BaseActivity() {

   private lateinit var  viewModel: VM
    abstract fun startObserve()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserve()
        initData()
        initView()

    }
}