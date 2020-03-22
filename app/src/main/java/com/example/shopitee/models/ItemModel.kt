package com.example.shopitee.models

import com.google.firebase.firestore.ServerTimestamp
import com.google.type.Date

data class ItemModel(
        val image: String = "",
        val title: String = "",
        val price: String = "") {

    constructor() : this("", "", "")
}