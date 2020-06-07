package com.example.shopitee.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopitee.HorizontalProductScrollModel
import com.example.shopitee.R
import com.example.shopitee.activities.CategoryActivity
import com.example.shopitee.db.ShopiteeDatabase
import com.example.shopitee.models.ItemCartModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.runBlocking
import java.security.AccessController.getContext

class ProductListAdapter(private val categoryModelList: MutableList<HorizontalProductScrollModel>) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>(), Filterable {
    private val searchableList: MutableList<HorizontalProductScrollModel> = mutableListOf()
    private val fulList: MutableList<HorizontalProductScrollModel> = mutableListOf()
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.category_item, viewGroup, false)
        fulList.addAll(categoryModelList)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHoldere: ViewHolder, postion: Int) {
        val currentItem = categoryModelList[postion]
        val icon = categoryModelList[postion].productImage
        val name = categoryModelList[postion].productTitle
        viewHoldere.setCategory(name, postion)
        viewHoldere.apply {
            categoryName.text = name

            Glide.with(itemView.context).load(currentItem.productImage).into(categoryIcon)
        }
    }

    override fun getItemCount(): Int {
        return categoryModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryIcon: ImageView = itemView.findViewById(R.id.categoryImage)
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        private fun setCategoryIcon() { //todo: set categoryIcons here;
        }

        fun setCategory(name: String, position: Int) {
            categoryName.text = name
            itemView.setOnClickListener {

                Toasty.info(itemView.context.applicationContext, "${categoryModelList[position].productTitle} added to cart.").show()
                runBlocking {
                    ShopiteeDatabase.getDatabase(itemView.context
                            .applicationContext).cartDao().insert(ItemCartModel(
                            name = categoryModelList[position].productTitle,
                            price = categoryModelList[position].productPrice,
                            image = categoryModelList[position].productImage
                    ))
                }
            }
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                searchableList.clear()
                if (constraint.isNullOrBlank()) {
                    searchableList.addAll(fulList)
                } else {
                    val searchResults = categoryModelList.forEach {
                        if(it.productTitle.toLowerCase().contains(constraint)  && !searchableList.contains(it))
                        {
                            searchableList.add(it)
                        }
                    }
                }
                return filterResults.also {
                    it.values = searchableList
                }
            }

            private var onNothingFound: (() -> Unit)? = null


            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                // no need to use "results" filtered list provided by this method.
                categoryModelList.clear()
                categoryModelList.addAll(elements = results?.values as MutableList<HorizontalProductScrollModel>)
                if (searchableList.isNullOrEmpty())
                    onNothingFound?.invoke()

                notifyDataSetChanged()

            }
        }
    }

}