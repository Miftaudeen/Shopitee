package com.example.shopitee

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cart_table")
data class ItemCartModel(
        val image: Int = R.mipmap.milk_bottles,
        val name: String = "Milk",
        val price: String = "₦1250/-",
        val created_at: Long = Calendar.getInstance().timeInMillis
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}