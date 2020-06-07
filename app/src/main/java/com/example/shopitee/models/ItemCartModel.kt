package com.example.shopitee.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shopitee.R
import java.util.*

@Entity(tableName = "cart_table")
data class ItemCartModel(
        val image: Int = R.mipmap.banner1,
        val name: String = "Milk",
        val price: String = "₦1250",
        val created_at: Long = Calendar.getInstance().timeInMillis
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}