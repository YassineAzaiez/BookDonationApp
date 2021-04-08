package com.example.booksdonationapp.presentation.commun


import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.booksdonationapp.core.NetworkState
import com.example.booksdonationapp.presentation.utils.hideKeyBoard
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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            hideKeyBoard()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun initConnexionListener() {
        NetworkState(this).observe(this, Observer {
            //TODO something when connectionOFF
        })
    }


}