package com.example.shopitee.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.shopitee.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.pixplicity.easyprefs.library.Prefs

class MainActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        SystemClock.sleep(3000)


    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth!!.currentUser
        if (currentUser == null) {
            val registerIntent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(registerIntent)
            finish()
        } else {
            val mainIntent = Intent(this@MainActivity, SplashActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }




}