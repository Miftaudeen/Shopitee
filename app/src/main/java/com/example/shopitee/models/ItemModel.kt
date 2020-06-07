package com.example.shopitee.models

import com.example.shopitee.R
import com.google.firebase.firestore.ServerTimestamp
import com.google.type.Date

data class ItemModel(
        val image: Int = R.mipmap.banner1,
        val title: String = "",
        val price: String = "") {

    constructor() : this(R.mipmap.banner1,"", "")
}