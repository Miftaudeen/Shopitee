package com.example.shopitee.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopitee.R
import com.example.shopitee.adapters.CartAdapter
import com.example.shopitee.db.ShopiteeDatabase
import com.example.shopitee.models.ItemCartModel
import com.flutterwave.raveandroid.RaveConstants
import com.flutterwave.raveandroid.RavePayActivity
import com.flutterwave.raveandroid.RavePayManager
import com.google.firebase.auth.FirebaseAuth
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.coroutines.*
import java.util.*


class CartActivity : AppCompatActivity() {
    val dummyItems = ArrayList<ItemCartModel>()
    private val selectedItems = ArrayList<ItemCartModel>()
    private lateinit var adapter: CartAdapter
    private val listPersonsSelected //keep track of selected objects
            : List<ItemCartModel>? = null
    private val listSelectedRows: List<View>? = null
    var deliveryFee = 0



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)




        adapter = CartAdapter(dummyItems)
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

                if(dummyItems.size>0) {
                    deliveryFee = 1000 * dummyItems.size
                    locationDisplay.setVisibility(View.VISIBLE)
                    locationDisplay.text = "Delivery fee is ₦${deliveryFee} " +
                            "to ${Prefs.getString("userAddress", "Apo, Abuja")}"
                }else{
                    locationDisplay.visibility = View.GONE
                }

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
                    locationDisplay.setVisibility(View.GONE)
                    recreate()
                }
            }

            R.id.clear_some -> {
                runBlocking {
                    adapter.deleteSelectedItems(applicationContext)
                    recreate()

                }
            }

            R.id.checkout -> {
                val amount = deliveryFee + dummyItems.sumByDouble { cartItem ->
                    cartItem.price.toDouble()
                }

                RavePayManager(this)
                        .setAmount(amount)
                        .setCountry("NG")
                        .setCurrency("NGN")
                        .setEmail(FirebaseAuth.getInstance().currentUser?.email ?: "maaruf.fun@gmail.com")
                        .setfName("Test")
                        .setlName("User")
                        .setNarration("Shopitee Checkout")
                        .setPublicKey("FLWPUBK_TEST-d899460c494b6ecf7ce25f63045a3d6d-X")
                        .setEncryptionKey("FLWSECK_TEST9c3bd5f19f88")
                        .setTxRef("Tx:${Random(5564)}")
                        .acceptCardPayments(true)
                        .allowSaveCardFeature(true)
                        .onStagingEnv(true)
                        .initialize()
            }


        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RaveConstants.RAVE_REQUEST_CODE -> {
                if (data != null) {
                    val message  = data.getStringExtra("response")
                    when (resultCode) {
                        RavePayActivity.RESULT_SUCCESS -> {
                            Toast.makeText(this, "SUCCESS, Transaction Complete", Toast.LENGTH_SHORT).show()
                            runBlocking {
                                ShopiteeDatabase.getDatabase(applicationContext).cartDao().deleteAll()
                                recreate()
                            }
                        }

                        RavePayActivity.RESULT_ERROR -> {
                            Toast.makeText(this, "ERROR: $message", Toast.LENGTH_SHORT).show()
                        }

                        RavePayActivity.RESULT_CANCELLED -> {
                            Toast.makeText(this, "CANCELLED: $message", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show()
            }

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

}




