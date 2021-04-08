package com.example.booksdonationapp.presentation.ui.registration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.RegistrationFragmentBinding
import com.example.booksdonationapp.presentation.commun.BaseVmFragment

class RegistrationFragment : BaseVmFragment<RegistrationViewModel>(RegistrationViewModel::class.java) {
    private lateinit var binding : RegistrationFragmentBinding
    override fun startObserve() {

    }

    override fun getLayoutResId(): View {
      binding = RegistrationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {

    }

    override fun initView() {

    }


}