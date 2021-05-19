package com.example.booksdonationapp.presentation.ui.createBook.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksdonationapp.R


private const val FIRST_STEP = 0
private const val SECOND_STEP = 1
private const val THIRD_STEP = 2

class CreationAdpater : RecyclerView.Adapter<BaseBookCreationHolder>() {


    override fun onBindViewHolder(holder: BaseBookCreationHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> FIRST_STEP
            1 -> SECOND_STEP
            2 -> THIRD_STEP
            else -> throw IllegalStateException("Unknown type")
        }
    }

    inner class FirstStepViewHolder(itemView: View) : BaseBookCreationHolder(itemView) {
        override fun bind() {

        }
    }

    inner class SecondStepViewHolder(itemView: View) : BaseBookCreationHolder(itemView) {
        override fun bind() {

        }
    }

    inner class ThirdStepViewHolder(itemView: View) : BaseBookCreationHolder(itemView) {
        override fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            FIRST_STEP -> FirstStepViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_create_book_first_step, parent, false)
            )
            SECOND_STEP -> SecondStepViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_create_book_first_step, parent, false)
            )
            THIRD_STEP -> ThirdStepViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_create_book_first_step, parent, false)
            )

            else -> throw  java.lang.IllegalStateException("Unknown type")
        }
}


