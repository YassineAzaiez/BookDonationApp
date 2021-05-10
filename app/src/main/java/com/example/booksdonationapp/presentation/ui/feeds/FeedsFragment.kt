package com.example.booksdonationapp.presentation.ui.feeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksdonationapp.databinding.FeedsFragmentBinding
import com.example.booksdonationapp.databinding.FeedsFragmentBinding.*
import com.example.booksdonationapp.presentation.commun.BaseVmFragment

class FeedsFragment : BaseVmFragment<FeedsViewModel,FeedsFragmentBinding>(FeedsViewModel::class.java) {



    override fun startObserve() {

    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): FeedsFragmentBinding {
        return  inflate(inflater,container,false)
    }


}