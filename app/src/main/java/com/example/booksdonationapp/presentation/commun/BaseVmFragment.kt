package com.example.booksdonationapp.presentation.commun


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.gamehub.commen.ui.BaseViewModel


abstract class BaseVmFragment<VM : BaseViewModel>(private val modelClass: Class<VM>) : Fragment() {



    abstract fun startObserve()



     lateinit var viewModel: VM

    abstract fun getLayoutResId(): View

    abstract fun initData()

    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return getLayoutResId()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        startObserve()
        initData()
    }



}