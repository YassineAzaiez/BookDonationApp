package com.example.gamehub.commen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel(){
    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
    }
}