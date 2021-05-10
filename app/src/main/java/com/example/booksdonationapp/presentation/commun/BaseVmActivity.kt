package com.example.booksdonationapp.presentation.commun

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.gamehub.commen.ui.BaseViewModel


abstract class BaseVmActivity<VM : BaseViewModel, VB : ViewBinding>(private val modelClass: Class<VM>) :
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