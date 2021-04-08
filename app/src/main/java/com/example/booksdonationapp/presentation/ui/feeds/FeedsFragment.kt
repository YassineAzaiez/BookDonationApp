package com.example.booksdonationapp.presentation.ui.feeds

import android.view.View
import com.example.booksdonationapp.databinding.FeedsFragmentBinding
import com.example.booksdonationapp.presentation.commun.BaseVmFragment

class FeedsFragment : BaseVmFragment<FeedsViewModel>(FeedsViewModel::class.java) {

    private lateinit var binding: FeedsFragmentBinding


    override fun startObserve() {

    }

    override fun initData() {

    }

    override fun initView() {

    }


    override fun getLayoutResId(): View {
        binding = FeedsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


}