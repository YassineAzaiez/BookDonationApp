package com.example.booksdonationapp.presentation.commun

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.example.booksdonationapp.presentation.commun.sharedPrefs.AppSHaredPref
import com.example.booksdonationapp.presentation.utils.screenHeight
import com.example.gamehub.commen.ui.BaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject


abstract class BaseSheet<VB : ViewBinding, VM : BaseViewModel> : BottomSheetDialogFragment() {

    @Inject
    protected lateinit var pref: AppSHaredPref


    abstract val fullHeight: Boolean
    private var _viewBinding: VB? = null

    protected val viewBinding: VB
        get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _viewBinding = createView(inflater, container)
        return _viewBinding?.root
    }

    abstract fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
    //    dialog.setOnShowListener(::adjustSheetHeight)
        setWhiteNavigationBar(dialog)
        return dialog
    }


    abstract fun setWhiteNavigationBar(dialog: Dialog)



    private fun adjustSheetHeight(dialog: DialogInterface) {
        val sheetLayout = (dialog as BottomSheetDialog)
            .findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        val sheetBehavior = BottomSheetBehavior.from(sheetLayout!!)
        if (!fullHeight) {
            sheetLayout.also { layout ->
                layout.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        } else {
            sheetLayout.also { layout ->
                layout.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            }

            sheetBehavior.also { behavior ->
                behavior.peekHeight = screenHeight
            }
        }
    }
}