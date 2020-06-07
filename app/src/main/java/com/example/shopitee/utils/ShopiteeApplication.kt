package com.example.shopitee.utils

import android.Manifest
import android.app.Application
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import co.paystack.android.PaystackSdk
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pixplicity.easyprefs.library.Prefs

class ShopiteeApplication: Application() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate() {
        super.onCreate()

        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()

        PaystackSdk.initialize(this)


    }
}