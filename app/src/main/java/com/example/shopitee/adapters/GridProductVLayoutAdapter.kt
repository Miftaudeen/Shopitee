package com.example.shopitee.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shopitee.HorizontalProductScrollModel
import com.example.shopitee.R
import com.example.shopitee.db.ShopiteeDatabase.Companion.getDatabase
import com.example.shopitee.models.ItemCartModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.runBlocking

class GridProductVLayoutAdapter(var horizontalProductScrollModelList: List<HorizontalProductScrollModel>) : BaseAdapter() {
    override fun getCount() = horizontalProductScrollModelList.size

    override fun getItem(position: Int): Any {
        return horizontalProductScrollModelList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val currentItem = horizontalProductScrollModelList[position]
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_scroll_item_layout, null)
            view.elevation = 0f
            view.setBackgroundColor(Color.parseColor("#ffffff"))
            val productImage = view.findViewById<ImageView>(R.id.h_s_product_image)
            val productTitle = view.findViewById<TextView>(R.id.h_s_product_title)
            val productDescription = view.findViewById<TextView>(R.id.h_s_product_decription)
            val productPrice = view.findViewById<TextView>(R.id.h_s_product_price)
            productImage.setImageResource(currentItem.productImage)
            productTitle.text = currentItem.productTitle
            productDescription.text = currentItem.productDescription
            productPrice.text = currentItem.productPrice
            view.setOnClickListener {
               Toasty.info(parent.context, "${currentItem.productTitle} added to cart.").show()
               runBlocking {
                   getDatabase(view.context
                           .applicationContext).cartDao().insert(ItemCartModel(
                           name = currentItem.productTitle,
                           price = currentItem.productPrice,
                           image = currentItem.productImage
                   ))
               }
            }
        } else {
            view = convertView
        }
        return view
    }

}