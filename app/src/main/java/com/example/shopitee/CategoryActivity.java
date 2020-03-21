package com.example.shopitee;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoryRecyclerView = findViewById(R.id.category_recylerview);


        //////// Banner slider

        List<SliderModel> sliderModelsList = new ArrayList<SliderModel>();
        sliderModelsList.add(new SliderModel(R.mipmap.banner2, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner3, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner, "#077AE4"));

        sliderModelsList.add(new SliderModel(R.mipmap.banner1, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner2, "#077AE4"));

        sliderModelsList.add(new SliderModel(R.mipmap.banner3, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner1, "#077AE4"));

        /////// Banner slider

        /////// Horizontal Product Layout

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.custom_error_icon, "3pc Suit", "Black Cotton", "Rs.6550/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.red_email, "3pc Suit", "Black Cotton", "Rs.6550/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.green_email, "3pc Suit", "Black Cotton", "Rs.6550/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.cart_black, "3pc Suit", "Black Cotton", "Rs.6550/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.custom_error_icon, "3pc Suit", "Black Cotton", "Rs.6550/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.bell, "3pc Suit", "Black Cotton", "Rs.6550/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.cart_black, "3pc Suit", "Black Cotton", "Rs.6550/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.custom_error_icon, "3pc Suit", "Black Cotton", "Rs.6550/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.cart_black, "3pc Suit", "Black Cotton", "Rs.6550/-"));

        /////// Horizontal Product Layout*/
        ///////////////////////
        LinearLayoutManager testingLayoutManger = new LinearLayoutManager(this);
        testingLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManger);

      List<HomePageModel> homePageModelList = new ArrayList<>();
       //homePageModelList.add(new HomePageModel(0, sliderModelsList));
          homePageModelList.add(new HomePageModel(1, R.mipmap.stripadd, "#000000"));
          //homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
          homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
       // homePageModelList.add(new HomePageModel(1, R.mipmap.stripadd, "#ff0000"));
      //  homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
        //homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
        //homePageModelList.add(new HomePageModel(1, R.mipmap.banner, "#ffff00"));
        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.splash_serach_icon) {
            // todo: search
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
