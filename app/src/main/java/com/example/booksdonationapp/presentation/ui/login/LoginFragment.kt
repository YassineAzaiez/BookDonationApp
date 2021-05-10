package com.example.booksdonationapp.presentation.ui.login

import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.LoginFragmentBinding.inflate
import com.example.booksdonationapp.databinding.LoginFragmentBinding
import com.example.booksdonationapp.presentation.commun.BaseVmFragment
import com.example.booksdonationapp.presentation.utils.*

class LoginFragment : BaseVmFragment<LoginViewModel,LoginFragmentBinding>(LoginViewModel::class.java) {


    override fun initView() {

        val click = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_LoginFragment_to_RegistrationFragment)
            }

        }
        viewBinding.tvNoAccount.text = getString(R.string.don_t_have_an_account_sign_up).underlinedBold(
            getString(R.string.sign_up),
            click
        )
        viewBinding.tvForgetPasswor.text =
            getString(R.string.forget_password).underlinedBold(
                getString(R.string.forget_password),
                click
            )

        viewBinding.btnSignIn.setOnClickListener {
            if (!viewBinding.ilEmail.removeInputLayoutError()
                    .isValidEMail(activity, viewBinding.tiEmail.text.toString())
                    .isNullOrEmpty() and !viewBinding.ilPassword.removeInputLayoutError()
                    .isValidPassword(
                        activity,
                        viewBinding.tiPassword.text.toString()
                    ).isNullOrEmpty()
            ) {
                findNavController().navigateSingleTop(
                    R.id.home_graph
                )
            }
        }

    }

    override fun startObserve() {

    }



    override fun initData() {

    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): LoginFragmentBinding {
        return inflate(inflater , container , false)
    }

}