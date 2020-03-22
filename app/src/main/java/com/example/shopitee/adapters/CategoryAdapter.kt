package com.example.shopitee.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopitee.R
import com.example.shopitee.activities.CategoryActivity
import com.example.shopitee.models.CategoryModel

class CategoryAdapter(private val categoryModelList: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.category_items, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHoldere: ViewHolder, postion: Int) {
        val icon = categoryModelList[postion].image
        val name = categoryModelList[postion].title
        viewHoldere.setCategory(name, postion)
        viewHoldere.apply {
            categoryName.text = name
        }
    }

    override fun getItemCount(): Int {
        return categoryModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryIcon: ImageView = itemView.findViewById(R.id.category_icon)
        val categoryName: TextView = itemView.findViewById(R.id.category_name)
        private fun setCategoryIcon() { //todo: set categoryIcons here;
        }

        fun setCategory(name: String, position: Int) {
            categoryName.text = name
            itemView.setOnClickListener {
                if (position != 0) {
                    val categoryIntent = Intent(itemView.context, CategoryActivity::class.java)
                    categoryIntent.putExtra("CategoryName", name)
                    itemView.context.startActivity(categoryIntent)
                }
            }
        }

    }

}