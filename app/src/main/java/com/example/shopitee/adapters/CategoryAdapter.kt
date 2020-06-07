package com.example.shopitee.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopitee.R
import com.example.shopitee.activities.CategoryActivity
import com.example.shopitee.models.CategoryModel

class CategoryAdapter(private val categoryModelList: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.category_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHoldere: ViewHolder, postion: Int) {
        val currentItem = categoryModelList[postion]
        val icon = categoryModelList[postion].image
        val name = categoryModelList[postion].title
        viewHoldere.setCategory(name, postion)
        viewHoldere.apply {
            categoryName.text = name

            Glide.with(itemView.context).load(currentItem.image).into(categoryIcon)
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

                    val categoryIntent = Intent(itemView.context, CategoryActivity::class.java)
                    categoryIntent.putExtra("CategoryName", name)
                    itemView.context.startActivity(categoryIntent)
            }
        }

    }

}