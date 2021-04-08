package com.example.booksdonationapp.presentation.utils.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.booksdonationapp.R

class CustomEditText @JvmOverloads  constructor(
    context: Context,
    attrs:AttributeSet? ,
    defStyleAttr : Int = 0
) : ConstraintLayout(context,attrs,defStyleAttr){



    init {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.custom_edit_text,this)
    }
}