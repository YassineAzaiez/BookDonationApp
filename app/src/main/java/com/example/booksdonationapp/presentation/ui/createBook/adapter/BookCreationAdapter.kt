package com.example.booksdonationapp.presentation.ui.createBook.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BookCreationAdapter(private val steps: List<Fragment>, context: FragmentActivity) :
    FragmentStateAdapter(context) {
    override fun getItemCount(): Int {
        return steps.size
    }

    override fun createFragment(position: Int) = steps[position]
}