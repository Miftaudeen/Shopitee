package com.example.shopitee

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopitee.adapters.ProductListAdapter
import com.example.shopitee.models.SliderModel
import kotlinx.android.synthetic.main.activity_category.*
import java.util.*

class ViewAll : AppCompatActivity() {
    private var horizontalProductScrollAdapter: ProductListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val title = intent.getStringExtra("CategoryName")
        supportActionBar!!.setTitle(title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        //////// Banner slider
        val sliderModelsList: MutableList<SliderModel> = ArrayList()
        sliderModelsList.add(SliderModel(R.mipmap.banner2, "#077AE4"))
        sliderModelsList.add(SliderModel(R.mipmap.banner3, "#077AE4"))
        sliderModelsList.add(SliderModel(R.mipmap.banner, "#077AE4"))
        sliderModelsList.add(SliderModel(R.mipmap.banner1, "#077AE4"))
        sliderModelsList.add(SliderModel(R.mipmap.banner2, "#077AE4"))
        sliderModelsList.add(SliderModel(R.mipmap.banner3, "#077AE4"))
        sliderModelsList.add(SliderModel(R.mipmap.banner, "#077AE4"))
        sliderModelsList.add(SliderModel(R.mipmap.banner1, "#077AE4"))

        /////// Banner slider

        /////// Horizontal Product Layout
        val horizontalProductScrollModelList: MutableList<HorizontalProductScrollModel> = ArrayList()
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.banner, "Yoyo Cocktails", "Black Cotton", "6550"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.cabbage, "Sweet Cabbages", "Black Cotton", "2550"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.milk_bottles, "Fresh Dairy", "Black Cotton", "3550"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.eggs, "Egg baskets", "Black Cotton", "1250"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.rice, "Rice", "Black Cotton", "20500"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.mamador, "Mamador Oil", "Black Cotton", "1400"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.banana, "Ripe Banana", "Black Cotton", "550"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.pasta, "Pasta", "Black Cotton", "4500"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.pepper, "Pepper", "Fresh red pepper", "300"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.coffee, "Coffee", "500g Black Coffee", "750"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.thyme, "Thyme", "Thyme", "650"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.titus, "Titus", "Canned titus", "250"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.milo, "Milo", "1 KG Cocoa Beverage", "1450"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.chiken_breast, "Chicken Breast", "Fresh Frozen Chicken Breast", "1500"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.carrot, "Carrot", "Fresh Carrot", "200"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.chicken, "Chicken", "Fresh Frozen Chicken", "1200"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.tomato_ketchup, "Tomato Ketchup", "Fresh Frozen Chicken", "800"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.peanut_butter, "Peanut Butter", "Peanut Butter", "700"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.salt, "Salt", "Mr Chef Salt", "100"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.salted_butter, "Salted Butter", "Salted Butter", "1200"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.avocados, "Avocados", "Avocados", "200"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.oranges, "Oranges", "Fresh Oranges", "600"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.bottled_water, "Bottled Water", "Pure Bottled Water", "1500"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.apple_juice, "Chicken", "Fresh Frozen Chicken", "1200"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.tea, "Tea", "Tea", "400"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.yogi_tea, "Yogi Tea", "Yogi Tea", "500"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.mayonnaise, "Mayonnaise", "Mayonnaise", "1300"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.onion, "Onion", "Onion", "300"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.fresh_tomato, "Fresh Tomato", "Fresh Tomato", "400"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.red_bull, "Red Bull drink", "Red Bull drink", "1200"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.red_seedless_grapes, "Seedless Grape", "Red Seedless Grape", "1200"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.rosemary_leaves, "Rosemary Leaves", "Rosemary Leaves", "1200"))
        horizontalProductScrollModelList.add(HorizontalProductScrollModel(R.mipmap.vinegar, "Vinegar", "Vinegar", "1200"))

        /////// Horizontal Product Layout*/
        ///////////////////////
        val testingLayoutManger = LinearLayoutManager(this)
        testingLayoutManger.orientation = LinearLayoutManager.VERTICAL
        category_recylerview.setLayoutManager(testingLayoutManger)
        horizontalProductScrollAdapter = ProductListAdapter(horizontalProductScrollModelList)
        category_recylerview.adapter = horizontalProductScrollAdapter
        horizontalProductScrollAdapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search_icon, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                print(newText)
                horizontalProductScrollAdapter?.getFilter()?.filter(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.splash_serach_icon) {
            // todo: search
            true
        } else super.onOptionsItemSelected(item)
    }
}