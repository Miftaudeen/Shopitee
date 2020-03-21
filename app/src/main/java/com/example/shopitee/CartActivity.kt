package com.example.shopitee

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopitee.db.ShopiteeDatabase
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.coroutines.*

class CartActivity : AppCompatActivity() {
    val dummyItems = mutableListOf<ItemCartModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        val adapter = CartAdapter(dummyItems)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(this)
        recycle.setHasFixedSize(true)

        runBlocking {
//            ShopiteeDatabase.getDatabase(applicationContext).cartDao().deleteAll()
        }

        GlobalScope.launch {
            val a = ShopiteeDatabase.getDatabase(applicationContext).cartDao().getEntireCart()

            withContext(Dispatchers.Main){
                dummyItems.clear()
                dummyItems.addAll(a)
                adapter.notifyDataSetChanged()
                Toast.makeText(this@CartActivity, "${a.size}", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.clear -> {
                runBlocking {
                    ShopiteeDatabase.getDatabase(applicationContext).cartDao().deleteAll()
                    recreate()
                }
            }


        }
        return true
    }
}