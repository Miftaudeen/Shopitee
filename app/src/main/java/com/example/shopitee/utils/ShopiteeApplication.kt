package com.example.shopitee.utils

import android.app.Application
import co.paystack.android.PaystackSdk

class ShopiteeApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        PaystackSdk.initialize(this)
    }
}