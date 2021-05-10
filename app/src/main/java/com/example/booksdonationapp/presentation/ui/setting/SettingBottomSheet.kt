package com.example.booksdonationapp.presentation.ui.setting

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.FragmentSettingBottomSheetBinding
import com.example.booksdonationapp.databinding.FragmentSettingBottomSheetBinding.inflate
import com.example.booksdonationapp.databinding.ItemSettingBinding
import com.example.booksdonationapp.presentation.commun.BaseSheet
import com.example.booksdonationapp.presentation.utils.readText
import com.example.booksdonationapp.presentation.utils.screenHeight
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingBottomSheet : BaseSheet<FragmentSettingBottomSheetBinding, MyViewModel>() {

    override val fullHeight: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewBinding.settingsContainer.also { container ->
            container.removeAllViews()
            container.addView(createNightModeSetting())
        }
    }

    private fun createNightModeSetting(): View {
        return ItemSettingBinding.inflate(layoutInflater).also { viewBinding ->
            viewBinding.settingDescText.also { view ->
                view.text = requireContext().readText(resId = R.string.night_mode)
            }

            viewBinding.toggleSwitch.also { switch ->
                switch.isChecked = pref.nightThemePref

                switch.setOnCheckedChangeListener { _, isChecked ->
                    onCheckedChangeNightMode(isChecked)
                }
            }
        }.root
    }

    private fun onCheckedChangeNightMode(isChecked: Boolean) {
        lifecycleScope.launch {
            pref.nightThemePref = isChecked
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES.takeIf { isChecked }
                ?: AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingBottomSheetBinding {
        return inflate(inflater, container, false)
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun setWhiteNavigationBar(dialog: Dialog) {
        val window = dialog.window
        if (window != null) {
            val metrics = DisplayMetrics()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val display = activity?.display
                display?.getRealMetrics(metrics)
            } else {
                @Suppress("DEPRECATION")
                val display = activity?.windowManager?.defaultDisplay
                @Suppress("DEPRECATION")
                display?.getMetrics(metrics)
            }
            val dimDrawable = GradientDrawable()
            // ...customize your dim effect here
            val navigationBarDrawable = GradientDrawable()
            navigationBarDrawable.shape = GradientDrawable.RECTANGLE
            //TODO use pref dark mode attribute to set the nav bar color
            navigationBarDrawable.setColor(Color.BLACK)
            val layers = arrayOf<Drawable>(dimDrawable, navigationBarDrawable)
            val windowBackground = LayerDrawable(layers)
            windowBackground.setLayerInsetTop(1, metrics.heightPixels)
            window.setBackgroundDrawable(windowBackground)
        }
    }


}




