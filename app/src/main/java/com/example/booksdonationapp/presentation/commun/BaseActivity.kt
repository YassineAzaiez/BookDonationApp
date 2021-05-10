package com.example.booksdonationapp.presentation.commun


import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.booksdonationapp.core.NetworkState
import com.example.booksdonationapp.presentation.utils.customViews.SnackBarManager
import com.example.booksdonationapp.presentation.utils.hideKeyBoard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutId(): View
    abstract fun initView()
    abstract fun initData()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initConnexionListener()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            hideKeyBoard()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun initConnexionListener() {
        NetworkState(this).observe(this, Observer { connected ->
            val container = findViewById<View>(android.R.id.content) as ViewGroup
            if (connected) {
                Log.d("test", "is connecte : $connected")

            }else{
                Log.d("test", "is connecte : $connected")
//                SnackBarManager.make(
//                   getLayoutId().rootView as ViewGroup,
//                    Snackbar.LENGTH_LONG
//                ).show()
            }
        })
    }


}