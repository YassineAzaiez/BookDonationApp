package com.example.booksdonationapp.presentation.ui.launcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {


    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }


    private fun initView() {

        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(
                R.id.action_WelcomeFragment_to_LoginFragment
            )
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(
                R.id.action_WelcomeFragment_to_RegistrationFragment
            )
        }


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) {
        }
    }
}