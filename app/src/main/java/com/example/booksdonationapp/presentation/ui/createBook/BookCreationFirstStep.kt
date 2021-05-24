package com.example.booksdonationapp.presentation.ui.createBook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.FragmentCreateBookFirstStepBinding
import com.example.booksdonationapp.databinding.FragmentCreateBookFirstStepBinding.inflate
import com.example.booksdonationapp.presentation.commun.BaseVmFragment
import com.example.booksdonationapp.presentation.utils.SharedUtils
import com.example.booksdonationapp.presentation.utils.gone
import com.example.booksdonationapp.presentation.utils.readText
import com.example.booksdonationapp.presentation.utils.visible
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow


class BookCreationFirstStep(private val goNext: () -> Unit) :
    BaseVmFragment<CreateNewBookViewModel, FragmentCreateBookFirstStepBinding>(
        CreateNewBookViewModel::class.java
    ) {

    private val vModel: CreateNewBookViewModel by viewModels()
    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateBookFirstStepBinding {
        return inflate(inflater, container, false)
    }

    override fun initData() {

    }

    override fun initView() {
        viewModel = vModel
        setCheckListener(viewBinding.chipsGroupLayout)

    }

    override fun startObserve() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            SharedUtils.getInstance().goToNextEvent.collect { it ->
                when (it) {
                    is MyEvent.GoToNextEvent -> {
                        if (it.event) goNext()




                    }
                }

            }
        }}
            private fun getChipsIds(chipGroup: ConstraintLayout): List<Int> {
                var chipsIds = ArrayList<Int>()
                chipGroup.forEach { view ->
                    if (view is Chip) {
                        chipsIds.add(view.id)
                    }
                }
                return chipsIds
            }


            private fun setCheckListener(chipGroup: ConstraintLayout) {
                chipGroup.forEach { view ->
                    if (view is Chip) {

                        view.setOnCheckedChangeListener { chip, isChecked ->

                            val chipsIds = getChipsIds(chipGroup)

                            view.setOnClickListener {
                                chipsIds.forEachIndexed { index, i ->

                                    (chipGroup.getChildAt(index) as Chip).isChecked = chip.id == i


                                }
                                if (isChecked && chip.tag.toString() == viewBinding.root.context.readText(
                                        R.string.sell
                                    )
                                ) {
                                    viewBinding.ilBookPrice.visible()
                                } else {
                                    viewBinding.ilBookPrice.gone()
                                }
                            }


                        }


                    }
                }
            }}


