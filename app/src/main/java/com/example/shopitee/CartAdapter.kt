package com.example.shopitee

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shopitee.CartAdapter.CartViewHolder
import com.example.shopitee.db.ShopiteeDatabase
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.coroutines.runBlocking

class CartAdapter(private var dummyItems: List<ItemCartModel>) : RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CartViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cart_item, viewGroup, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(cartViewHolder: CartViewHolder, i: Int) {
        val currentItem = dummyItems[i]

        cartViewHolder.apply {
            productName.text = currentItem.name
            productPrice.text = currentItem.price
            image.setImageResource(currentItem.image)

        }

    }
    override fun getItemCount() = dummyItems.size

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.productImage
        val productName: TextView = itemView.productName
        val productPrice: TextView = itemView.productPrice

    }
}