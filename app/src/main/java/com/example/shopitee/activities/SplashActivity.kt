package com.example.shopitee.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.shopitee.HomeFragment
import com.example.shopitee.R
import com.example.shopitee.SignInFragment
import com.example.shopitee.ViewAll
import com.example.shopitee.fragments.CategoriesFragment.Companion.newInstance
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.pixplicity.easyprefs.library.Prefs
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val REQUEST_CHECK_SETTINGS: Int = 0x1
    private var frameLayout: FrameLayout? = null
    var mAuth: FirebaseAuth? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        mAuth = FirebaseAuth.getInstance()
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.menu.getItem(0).isChecked = true
        val name = navigationView.getHeaderView(0).findViewById<TextView>(R.id.splash_fullname)
        val email = navigationView.getHeaderView(0).findViewById<TextView>(R.id.splash_email)
        if (mAuth!!.currentUser != null) {
            val currentUserName = mAuth!!.currentUser!!.displayName
            val currentUserEmail = mAuth!!.currentUser!!.email
            name.text = currentUserName
            email.text = currentUserEmail
        }
        frameLayout = findViewById(R.id.splash_framelayout)
        setFragment(HomeFragment())

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    127)

        } else {
            if(isLocationEnabled(applicationContext)) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->

                    runBlocking {
                        createLocationRequest(location)
                    }

                }
            }else{
                 AlertDialog.Builder(this)
                        .setMessage(R.string.gps_network_not_enabled)
                        .setPositiveButton(
                                R.string.open_location_settings
                        ) { dialog, which ->
                            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            }else{
                                finishAffinity()
                            }
                        }.setNegativeButton("No"){dialog, which ->
                             finishAffinity()
                         }
            }
        }
    }

    private fun saveLocation(location: Location) {
        val results = Geocoder(this).getFromLocation(location.latitude, location.longitude, 1)
        val finalResult = results[0]
        //                Toasty.info(this, finalResult.getAddressLine(1))
        Log.e("Address", finalResult.toString())
        Prefs.putString("userAddress", finalResult.getAddressLine(0))
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }

    fun createLocationRequest(location: Location) {
        val locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = locationRequest?.let {
            LocationSettingsRequest.Builder()
                .addLocationRequest(it)
        }

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder?.build())

        task.addOnSuccessListener { locationSettingsResponse ->
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
            saveLocation(location)
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException){
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(this@SplashActivity,
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.splash, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.splash_serach_icon) { // todo: search
            return true
        } else if (id == R.id.splash_notification_icon) { // todo: notification
        } else if (id == R.id.splash_cart_icon) { // todo: cart
            startActivity(Intent(this@SplashActivity, CartActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_my_tailor) {
            setFragment(HomeFragment())
        } else if (id == R.id.nav_my_orders) {
            startActivity(Intent(this@SplashActivity, CartActivity::class.java))// Handle the camera action
        } else if (id == R.id.nav_my_cart) {
            startActivity(Intent(this@SplashActivity, CartActivity::class.java))
        } else if (id == R.id.nav_my_account) {
            startActivity(Intent(this@SplashActivity, AccountActivity::class.java))
        } else if (id == R.id.nav_sign_out) {
            mAuth!!.signOut()
            setFragment(SignInFragment())
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(frameLayout!!.id, fragment)
        fragmentTransaction.commit()
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            127 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                        val results = Geocoder(this).getFromLocation(location.latitude, location.longitude, 1)
                        val finalResult = results[0]
                        Prefs.putString("userAddress", "${finalResult.adminArea} ${finalResult.locality}, ${finalResult.countryName}")
                    }

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    fun openViewAll(view: View) {
        startActivity(Intent(this@SplashActivity, ViewAll::class.java))
    }
}