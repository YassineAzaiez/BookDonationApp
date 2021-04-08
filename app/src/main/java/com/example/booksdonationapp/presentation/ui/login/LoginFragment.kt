package com.example.booksdonationapp.presentation.ui.login

import android.text.style.ClickableSpan
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.LoginFragmentBinding
import com.example.booksdonationapp.presentation.commun.BaseVmFragment
import com.example.booksdonationapp.presentation.utils.*

class LoginFragment : BaseVmFragment<LoginViewModel>(LoginViewModel::class.java) {
    private lateinit var binding: LoginFragmentBinding


    override fun initView() {

        val click = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_LoginFragment_to_RegistrationFragment)
            }

        }
        binding.tvNoAccount.text = getString(R.string.don_t_have_an_account_sign_up).underlinedBold(
            getString(R.string.sign_up),
            click
        )
        binding.tvForgetPasswor.text =
            getString(R.string.forget_password).underlinedBold(
                getString(R.string.forget_password),
                click
            )

        binding.btnSignIn.setOnClickListener {
            if (!binding.ilEmail.removeInputLayoutError()
                    .isValidEMail(activity, binding.tiEmail.text.toString())
                    .isNullOrEmpty() and !binding.ilPassword.removeInputLayoutError()
                    .isValidPassword(
                        activity,
                        binding.tiPassword.text.toString()
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

    override fun getLayoutResId(): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {

    }

}