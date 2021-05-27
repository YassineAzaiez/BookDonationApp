package com.example.booksdonationapp.presentation.ui.createBook

import android.location.Address
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.BookCreationSecondStepBinding
import com.example.booksdonationapp.databinding.BookCreationSecondStepBinding.inflate
import com.example.booksdonationapp.presentation.commun.BaseVmFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mustufamedium.geocoderexample.ResponseStatus


class BookCreationSecondStep(goNext: () -> Unit) :
    BaseVmFragment<CreateNewBookViewModel, BookCreationSecondStepBinding>(CreateNewBookViewModel::class.java),
    OnMapReadyCallback, GoogleMap.OnCameraIdleListener {
    private var TUNISA_LOCATION = LatLng(33.8869, 9.5375)
    private lateinit var mGoogleMap: GoogleMap
    lateinit var address: String
    private val vModel: CreateNewBookViewModel by viewModels()
    // private val coder = Geocoder(requireContext())

    override fun startObserve() {
        viewModel.getLocationInformation.observe(this, Observer {
            it?.let {
                when (it.status) {
                    ResponseStatus.ERROR -> {
                       viewBinding.tiUserAdr.setText("Location not found!")
                    }
                    ResponseStatus.LOADING -> {
                        viewBinding.tiUserAdr.setText("Searching...")
                    }
                    ResponseStatus.SUCCESS -> {
                        it.data?.let { model ->
                            viewBinding.tiUserAdr.setText(model.locationAddress)

                        }
                    }
                }
            }
        })
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BookCreationSecondStepBinding {
        return inflate(inflater, container, false)
    }

    override fun initData() {

    }

    override fun initView() {
        viewModel = vModel
        initMapView()
    }


    private fun initMapView() {
        val mapFragment = this.childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.mGoogleMap = googleMap
        this.mGoogleMap.clear()
        moveCamera()
        mGoogleMap.setOnCameraIdleListener(this)

    }

    private fun moveCamera() {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TUNISA_LOCATION, 6.2F))
    }

    private fun setMarkerOnMap(address: Address) {
        mGoogleMap.addMarker(
            MarkerOptions()
                .position(LatLng(address.latitude, address.longitude))
                .title(viewBinding.tiUserAdr.toString())
        )
    }

    override fun onCameraIdle() {
        mGoogleMap?.let {
            it.cameraPosition?.let { position ->
                TUNISA_LOCATION = mGoogleMap?.cameraPosition!!.target
                viewModel.getLocationInfo(
                    activity,
                    TUNISA_LOCATION.latitude.toString(),
                    TUNISA_LOCATION.longitude.toString()
                )
            }

        }
    }
}