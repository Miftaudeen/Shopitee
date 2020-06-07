package com.example.shopitee.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopitee.R
import com.example.shopitee.adapters.CartAdapter.CartViewHolder
import com.example.shopitee.db.ShopiteeDatabase
import com.example.shopitee.models.ItemCartModel
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.coroutines.runBlocking


class CartAdapter(private var dummyItems: ArrayList<ItemCartModel>) : RecyclerView.Adapter<CartViewHolder>() {

    private val listSelectedRows = mutableListOf<View>();
    val dummyItemsSelected = mutableListOf<ItemCartModel>();

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CartViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cart_item, viewGroup, false)
        return CartViewHolder(view).listen{ pos, type ->

            handleLongPress(pos, view)
        }
    }


    fun handleLongPress(position: Int, view: View) {
        if (listSelectedRows.contains(view)) {
            listSelectedRows.remove(view)
            dummyItemsSelected.remove(dummyItems.get(position))
            view.background = ColorDrawable(Color.WHITE)
        } else {
            dummyItemsSelected.add(dummyItems.get(position))
            println(dummyItemsSelected)
            listSelectedRows.add(view)
            view.background = ColorDrawable(Color.GRAY)
        }
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

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    fun deleteSelectedItems(applicationContext: Context) {
        for(i in dummyItemsSelected){
            runBlocking {
                ShopiteeDatabase.getDatabase(applicationContext).cartDao().deleteItem(i.id.toString())
                dummyItems.remove(i)
            }

        }
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.productImage
        val productName: TextView = itemView.productName
        val productPrice: TextView = itemView.productPrice

    }
}