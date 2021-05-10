package com.example.booksdonationapp.presentation.utils.customViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.booksdonationapp.R
import com.google.android.material.snackbar.BaseTransientBottomBar

class SnackBarManager(
    parent: ViewGroup,
    content: CustomSnackBar
) : BaseTransientBottomBar<SnackBarManager>(parent, content, content) {

    companion object {
        fun make(viewGroup: ViewGroup, duration: Int): SnackBarManager {
            val customView = LayoutInflater.from(viewGroup.context).inflate(
                R.layout.layout_snackbar,
                viewGroup,
                true
            ) as CustomSnackBar



            return SnackBarManager(viewGroup, customView).setDuration(duration)
        }
    }
}