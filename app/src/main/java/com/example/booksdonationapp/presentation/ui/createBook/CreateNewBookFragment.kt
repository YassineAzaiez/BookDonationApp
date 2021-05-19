package com.example.booksdonationapp.presentation.ui.createBook

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.CreateNewBookFragmentBinding
import com.example.booksdonationapp.databinding.CreateNewBookFragmentBinding.inflate
import com.example.booksdonationapp.presentation.MainActivity
import com.example.booksdonationapp.presentation.commun.BaseVmFragment
import com.example.booksdonationapp.presentation.ui.createBook.adapter.CreationAdpater
import com.zhpan.indicator.enums.IndicatorSlideMode.Companion.WORM

class CreateNewBookFragment :
    BaseVmFragment<CreateNewBookViewModel, CreateNewBookFragmentBinding>(CreateNewBookViewModel::class.java) {
    private val bookCreationAdapter by lazy {
        CreationAdpater()
    }

    override fun startObserve() {

    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CreateNewBookFragmentBinding {
        return inflate(inflater, container, false)
    }

    override fun initData() {

    }

    override fun initView() {
        (activity as MainActivity)?.apply {
            hideNavigation()

        }
        initViewPager()

        with(viewBinding.viewPagerIndicator) {
            setSliderColor(
                activity.getColor(R.color.white_EEEEEE),
                activity.getColor(R.color.yellow_fdc830)
            )
            setSliderWidth(40F)
            setSliderHeight(15f)
            setSlideMode(WORM)
            setupWithViewPager(viewBinding.bookCreationPager)
        }
    }

    private fun initViewPager() {
        with(viewBinding.bookCreationPager) {
            adapter = bookCreationAdapter
            orientation = androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
          //  isUserInputEnabled = false

        }
    }
}