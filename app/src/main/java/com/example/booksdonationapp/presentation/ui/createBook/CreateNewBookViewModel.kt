package com.example.booksdonationapp.presentation.ui.createBook


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.booksdonationapp.presentation.ui.createBook.MyEvent.GoToNextEvent
import com.example.booksdonationapp.presentation.utils.mapUtils.GeoCoderUtil
import com.example.booksdonationapp.presentation.utils.mapUtils.LoadDataCallback
import com.example.booksdonationapp.presentation.utils.mapUtils.LocationModel
import com.example.gamehub.commen.ui.BaseViewModel
import com.mustufamedium.geocoderexample.ResponseStatusCallbacks
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



    private val _getLocationInformation = MutableLiveData<ResponseStatusCallbacks<LocationModel>>()
    val getLocationInformation: LiveData<ResponseStatusCallbacks<LocationModel>>
        get() = _getLocationInformation

    fun getLocationInfo(context: Context, latitude: String, longitude: String) {
        _getLocationInformation.value = ResponseStatusCallbacks.loading(data = null)
        GeoCoderUtil.execute(context, latitude, longitude, object :
            LoadDataCallback<LocationModel> {
            override fun onDataLoaded(response: LocationModel) {
                _getLocationInformation.value = ResponseStatusCallbacks.success(response)
            }
            override fun onDataNotAvailable(errorCode: Int, reasonMsg: String) {
                _getLocationInformation.value =
                    ResponseStatusCallbacks.error(data = null, message = "Something went wrong!")
            }
        })
    }

}