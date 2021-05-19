package com.example.booksdonationapp.presentation.ui.feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.booksdonationapp.databinding.FeedsFragmentBinding
import com.example.booksdonationapp.databinding.FeedsFragmentBinding.inflate
import com.example.booksdonationapp.presentation.MainActivity
import com.example.booksdonationapp.presentation.commun.BaseVmFragment

class FeedsFragment : BaseVmFragment<FeedsViewModel,FeedsFragmentBinding>(FeedsViewModel::class.java) {



    override fun startObserve() {

    }

    override fun initData() {

    }

    override fun initView() {
        (activity as MainActivity)?.apply {
            showNavigation()
        }
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): FeedsFragmentBinding {
        return  inflate(inflater,container,false)
    }


}