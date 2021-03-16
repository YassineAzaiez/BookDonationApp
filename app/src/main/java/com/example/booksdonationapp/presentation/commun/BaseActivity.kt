package com.example.booksdonationapp.presentation.commun


import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.booksdonationapp.core.NetworkState

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {




    abstract fun getLayoutId(): View
    abstract fun initView()
    abstract fun initData()

    //TODO add connexion listener method
    //TODO loading method

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
    }

    private fun initConnexionListener() {
        NetworkState(this).observe(this, Observer {
            //TODO something when connectionOFF
        })
    }


}