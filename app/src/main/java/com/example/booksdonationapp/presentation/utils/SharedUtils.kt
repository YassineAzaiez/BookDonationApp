package com.example.booksdonationapp.presentation.utils

import androidx.lifecycle.MutableLiveData
import com.example.booksdonationapp.presentation.ui.createBook.MyEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class SharedUtils {

     var toolbarTitle = MutableLiveData<String>()
    private val _goToNExtEvent =  Channel<MyEvent.GoToNextEvent>()
     val goToNextEvent = _goToNExtEvent.receiveAsFlow()

    companion object {
        @Volatile
        var INSTANCE: SharedUtils? = null
        fun getInstance(): SharedUtils = INSTANCE ?: synchronized(this) {
            SharedUtils().also {
                INSTANCE = it
            }
        }

    }
    suspend fun sendGoToNext(){
        _goToNExtEvent.send(MyEvent.GoToNextEvent(true))
    }

    fun updateTitle(title : String){
        toolbarTitle.postValue(title)
    }
}