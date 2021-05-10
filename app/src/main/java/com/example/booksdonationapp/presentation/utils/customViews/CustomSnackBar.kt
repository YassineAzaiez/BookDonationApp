package com.example.booksdonationapp.presentation.utils.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.booksdonationapp.R
import com.google.android.material.snackbar.ContentViewCallback

class CustomSnackBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    var message: TextView

    init {
        View.inflate(context, R.layout.layout_snackbar, this)
        message = findViewById(R.id.message)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        AlphaAnimation(0F, 1F).apply {
            interpolator = DecelerateInterpolator()
            setDuration(500)
        }.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {
        AlphaAnimation(1F, 0F).apply {
            interpolator = AccelerateInterpolator()
            setDuration(500)
        }.start()
    }
}