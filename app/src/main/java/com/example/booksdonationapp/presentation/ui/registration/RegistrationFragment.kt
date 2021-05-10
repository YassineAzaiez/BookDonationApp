package com.example.booksdonationapp.presentation.ui.registration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.RegistrationFragmentBinding
import com.example.booksdonationapp.databinding.RegistrationFragmentBinding.*
import com.example.booksdonationapp.presentation.commun.BaseVmFragment
import com.example.booksdonationapp.presentation.utils.isValidEMail
import com.example.booksdonationapp.presentation.utils.isValidPassword
import com.example.booksdonationapp.presentation.utils.validInputStringOnly

class RegistrationFragment : BaseVmFragment<RegistrationViewModel ,RegistrationFragmentBinding>(RegistrationViewModel::class.java) {

    private val inputs = ArrayList<String>()
    override fun startObserve() {

    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): RegistrationFragmentBinding {
        return inflate(inflater,container,false)
    }

    override fun initData() {

    }

    override fun initView() {
       viewBinding.ivGoBack.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_RegistrationFragment_to_WelcomeFragment
            )
        )
       //
        viewBinding.btnSignIn.setOnClickListener {
           if(!initInputs().any { it.isNullOrEmpty() }){
               findNavController().navigate(
                   R.id.action_RegistrationFragment_to_WelcomeFragment //TODO change the action
               )
           }
        }

    }

    private fun initInputs() : List<String> {

        viewBinding.apply {
            inputs.apply {
                add(
                    ilFirstName.validInputStringOnly(
                        activity,
                        tiFirstName.text.toString()
                    )
                )

                add(
                    ilSurName.validInputStringOnly(
                        activity,
                        tiSUrName.text.toString()
                    )
                )
                add(ilEmail.isValidEMail(activity, viewBinding.tiEmail.text.toString()))
                add(
                    ilPassword.isValidPassword(
                        activity,
                        viewBinding.tiPassword.text.toString()
                    )
                )
                add(tiPhoneNumber.text.toString())
            }
        }
        return  inputs
    }


}