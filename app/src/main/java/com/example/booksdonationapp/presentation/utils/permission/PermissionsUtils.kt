package com.example.booksdonationapp.presentation.utils.permission

import android.Manifest.permission
import android.content.Context
import com.example.booksdonationapp.presentation.commun.BaseActivity
import pub.devrel.easypermissions.EasyPermissions

class PermissionsUtils {
    companion object {
        const val RC_LOCATION_PERM = 100
        const val RC_WRITE_PERM = 101
        const val RC_READ_PERM = 102
    }

    fun requestDeniedPermission(context: Context, requestCode: Int) {

    }


    fun requestLocationPermission(context: BaseActivity) {
        if (!hasLocationPermission(context))
            EasyPermissions.requestPermissions(
                context,
                "This app requires location access",
                RC_LOCATION_PERM,
                permission.ACCESS_FINE_LOCATION
            )
    }

    fun hasLocationPermission(context: Context): Boolean {
        return EasyPermissions.hasPermissions(context, permission.ACCESS_FINE_LOCATION)
    }
}