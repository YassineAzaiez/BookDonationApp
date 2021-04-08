package com.example.booksdonationapp.presentation.utils

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.example.booksdonationapp.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

const val PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

fun TextInputLayout.isValidEMail(
    context: Context,
    email: String
): String {

    return if (TextUtils.isEmpty(email)) {
        setInputErrorMsg(this, context.getString(R.string.filedRequired))
        ""
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        setInputErrorMsg(this, context.getString(R.string.invalidEmailErrorMsg))
        ""
    } else email


}

fun TextInputLayout.isValidPassword(
    context: Context,
    password: String
): String {
    val pattern = Pattern.compile(PASSWORD_PATTERN)
    return if (TextUtils.isEmpty(password)) {

        setInputErrorMsg(this, context.getString(R.string.filedRequired))
        ""
    } else if (!pattern.matcher(password).matches() && !TextUtils.isEmpty(password)) {
        setInputErrorMsg(
            this,
            if (this.editText?.text.toString().length < 8) context.getString(R.string.shortPasswordErrorMsg) else context.getString(
                R.string.wrongFormatPasswordErrorMsg
            )
        )
        ""
    } else password
}

private fun setInputErrorMsg(
    textInputLayout: TextInputLayout,
    errorMsg: String?
) {
    textInputLayout.error = errorMsg
}

 fun TextInputLayout.removeInputLayoutError(

) :TextInputLayout{

    this.editText?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            error = ""
            background = null
            errorIconDrawable = null

        }
    }
    return this
}