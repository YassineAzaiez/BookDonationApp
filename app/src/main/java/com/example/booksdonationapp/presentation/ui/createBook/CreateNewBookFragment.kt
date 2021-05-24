package com.example.booksdonationapp.presentation.ui.createBook

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.CreateNewBookFragmentBinding
import com.example.booksdonationapp.databinding.CreateNewBookFragmentBinding.inflate
import com.example.booksdonationapp.presentation.MainActivity
import com.example.booksdonationapp.presentation.commun.BaseVmFragment
import com.example.booksdonationapp.presentation.ui.createBook.adapter.BookCreationAdapter
import com.example.booksdonationapp.presentation.utils.SharedUtils
import com.zhpan.indicator.enums.IndicatorSlideMode.Companion.WORM

class CreateNewBookFragment :
    BaseVmFragment<CreateNewBookViewModel, CreateNewBookFragmentBinding>(CreateNewBookViewModel::class.java) {
    private val creationSteps = listOf<Fragment>(
        BookCreationFirstStep { goToNextPage() },
       BookCreationSecondStep { goToNextPage() },
        BookCreationFirstStep { goToNextPage() }
    )
    val vModel: CreateNewBookViewModel by viewModels()
    private val bookCreationAdapter by lazy {
        BookCreationAdapter(creationSteps, activity)
    }

    private var viewPagerPostion = 0

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
        viewModel = vModel
        (activity as MainActivity)?.apply {
            hideNavigation()

        }
        initClickListeners()
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
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            isUserInputEnabled = false
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewPagerPostion = position
                }
            })

        }
    }

    private fun initClickListeners() {
        viewBinding.ivPrevious.setOnClickListener {
            goToPreviousPage()
        }

        viewBinding.btnContinue.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                SharedUtils.getInstance().sendGoToNext()



            }




        }

    }

    private fun goToNextPage() {
        when (viewPagerPostion) {
            0, 1 -> viewBinding.bookCreationPager.currentItem = viewPagerPostion.inc()
            else -> Log.i("last page reached", "")
        }
    }

    private fun goToPreviousPage() {
        when (viewPagerPostion) {
            1, 2 -> viewBinding.bookCreationPager.currentItem = viewPagerPostion.dec()
            else -> findNavController().popBackStack()
        }
    }
}