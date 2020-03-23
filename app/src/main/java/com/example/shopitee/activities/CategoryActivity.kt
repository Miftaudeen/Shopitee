package com.example.shopitee.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopitee.R
import com.example.shopitee.models.ItemModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_sign_in.*

class CategoryActivity : AppCompatActivity() {
    lateinit var categoryRecyclerView: RecyclerView
    lateinit var adapter: FirestoreRecyclerAdapter<*, *>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val title = intent.getStringExtra("CategoryName") ?: "Dairy"

        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        categoryRecyclerView = findViewById(R.id.category_recyclerview)

//        categoryRecyclerView = findViewById(R.id.category_recyclerview)

        val items = mutableListOf<ItemModel>()

        categoryRecyclerView.layoutManager = LinearLayoutManager(this)

        val query = FirebaseFirestore.getInstance().collection("products")
//                .whereEqualTo("category", title.toLowerCase())

        val options = FirestoreRecyclerOptions.Builder<ItemModel>()
                .setQuery(query, ItemModel::class.java)
                .build()

        adapter = object:  FirestoreRecyclerAdapter<ItemModel, ItemHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
                val view = LayoutInflater.from(this@CategoryActivity).inflate(R.layout.cart_item, parent, false)

                return ItemHolder(view)
            }

            override fun onBindViewHolder(holder: ItemHolder, position: Int, model: ItemModel) {
                holder.apply {
                    price.text = model.price
                    name.text = model.title
                    Glide.with(this@CategoryActivity).load(model.image).into(image)
                }
            }

        }

        categoryRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val price : TextView = itemView.findViewById(R.id.productPrice)
        val name: TextView = itemView.findViewById(R.id.productName)
        val image: ImageView = itemView.findViewById(R.id.productImage)
    }
}