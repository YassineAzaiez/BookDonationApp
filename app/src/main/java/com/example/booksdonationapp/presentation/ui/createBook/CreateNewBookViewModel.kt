package com.example.booksdonationapp.presentation.ui.createBook


import androidx.lifecycle.viewModelScope
import com.example.booksdonationapp.presentation.ui.createBook.MyEvent.GoToNExtEvent
import com.example.gamehub.commen.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MyEvent() {
    data class GoToNExtEvent(val event: Boolean) : MyEvent()
}

@HiltViewModel
class CreateNewBookViewModel @Inject constructor() : BaseViewModel() {

    private val _goNextTriggerChannel = Channel<MyEvent>()
    val goNextTrigger = _goNextTriggerChannel.receiveAsFlow()


    fun triggerGoNextEvent() = viewModelScope.launch {
        _goNextTriggerChannel.send(GoToNExtEvent(true))
    }

}