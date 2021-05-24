package com.example.booksdonationapp.presentation.ui.createBook

import android.location.Address
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.booksdonationapp.R
import com.example.booksdonationapp.databinding.BookCreationSecondStepBinding
import com.example.booksdonationapp.databinding.BookCreationSecondStepBinding.inflate
import com.example.booksdonationapp.presentation.commun.BaseVmFragment
import com.example.booksdonationapp.presentation.utils.hideKeyBoard
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.OkHttpClient


class BookCreationSecondStep(goNext: () -> Unit) :
    BaseVmFragment<CreateNewBookViewModel, BookCreationSecondStepBinding>(CreateNewBookViewModel::class.java),
    OnMapReadyCallback {
    private val TUNISA_LOCATION = LatLng(33.8869, 9.5375)
    private lateinit var googleMap: GoogleMap
    lateinit var address: String
   // private val coder = Geocoder(requireContext())

    override fun startObserve() {

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
        viewBinding.tvTarget.setOnClickListener {
            activity.hideKeyBoard()
            address = viewBinding.tiUserAdr.toString()
        }
        initMapView()
    }


    private fun initMapView() {
        val mapFragment = this.childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TUNISA_LOCATION, 6.2F))
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }

    private fun setMarkerOnMap(address: Address) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(address.latitude, address.longitude))
                .title(viewBinding.tiUserAdr.toString())
        )
    }
}