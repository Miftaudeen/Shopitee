package com.example.shopitee.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.shopitee.R
import com.example.shopitee.adapters.CartAdapter.CartViewHolder
import com.example.shopitee.models.ItemCartModel
import kotlinx.android.synthetic.main.cart_item.view.*

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
            Glide.with(cartViewHolder.itemView.context).load(currentItem.image).into(image)

        }

    }
    override fun getItemCount() = dummyItems.size

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.productImage
        val productName: TextView = itemView.productName
        val productPrice: TextView = itemView.productPrice

    }
}