package com.example.booksdonationapp.presentation.ui.createBook


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.booksdonationapp.presentation.ui.createBook.MyEvent.GoToNextEvent
import com.example.gamehub.commen.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MyEvent() {
    data class GoToNextEvent(val event: Boolean) : MyEvent()
}

@HiltViewModel
class CreateNewBookViewModel @Inject constructor() : BaseViewModel() {





}