package com.example.shopitee;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopitee.adapters.CategoryAdapter;
import com.example.shopitee.adapters.HomePageAdapter;
import com.example.shopitee.models.CategoryModel;
import com.example.shopitee.models.HomePageModel;
import com.example.shopitee.models.SliderModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.home_category_recylerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryRecyclerView.setVisibility(View.GONE);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link", "Home"));
        categoryModelList.add(new CategoryModel("link", "Brands"));
        categoryModelList.add(new CategoryModel("link", "Designing"));
        categoryModelList.add(new CategoryModel("link", "T-Shirt Printing"));
        categoryModelList.add(new CategoryModel("link", "Casual Shawls"));
        categoryModelList.add(new CategoryModel("link", "Drinks"));
        categoryModelList.add(new CategoryModel("link", "Food stuffs"));
        categoryModelList.add(new CategoryModel("link", "Vegetables"));
        categoryModelList.add(new CategoryModel("link", "Cooking Ingredients"));
        categoryModelList.add(new CategoryModel("link", "Oils"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        //////// Banner slider

        List<SliderModel> sliderModelsList = new ArrayList<SliderModel>();
        sliderModelsList.add(new SliderModel(R.mipmap.banner2, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner3, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner4, "#077AE4"));

        sliderModelsList.add(new SliderModel(R.mipmap.banner1, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner2, "#077AE4"));

        sliderModelsList.add(new SliderModel(R.mipmap.banner3, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner, "#077AE4"));
        sliderModelsList.add(new SliderModel(R.mipmap.banner1, "#077AE4"));
        /*sliderModelsList.add(new SliderModel(R.mipmap.mark));
        sliderModelsList.add(new SliderModel(R.mipmap.cart_black));

        sliderModelsList.add(new SliderModel(R.mipmap.red_email));
        sliderModelsList.add(new SliderModel(R.mipmap.home_icon));
        sliderModelsList.add(new SliderModel(R.mipmap.green_email));
        sliderModelsList.add(new SliderModel(R.mipmap.add_profile_picture));
        sliderModelsList.add(new SliderModel(R.mipmap.error_icon));
        sliderModelsList.add(new SliderModel(R.mipmap.add1));
        sliderModelsList.add(new SliderModel(R.mipmap.mark));
        sliderModelsList.add(new SliderModel(R.mipmap.cart_black));


        sliderModelsList.add(new SliderModel(R.mipmap.red_email));
        sliderModelsList.add(new SliderModel(R.mipmap.home_icon));*/


        /////// Banner slider

        /////// Horizontal Product Layout

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//        horizontalProductScrollModelList.add(
//                new HorizontalProductScrollModel(R.mipmap.custom_error_icon, "3pc Suit",
//                        "Black Cotton", "6550"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.banner, "Yoyo Cocktails", "All natural flavors", "6550"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.cabbage, "Sweet Cabbages", "Freshly harvested cabbages", "2550"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.milk_bottles, "Fresh Dairy", "Freshly milked bottles", "3550"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.eggs, "Egg basket", "Freshly laid eggs", "1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.bread, "Chocolate Bread", "A 1.5kg loaf of chocolate Bread", "650"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.banana, "Ripe Banana", "A bunch of ripe", "550"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.pasta, "Pasta", "A carton of Dangote Pasta", "4500"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.rice, "Rice", "50Kg bag of Nigerian rice", "20500"));

        /////// Horizontal Product Layout
        ///////////////////////
        testing = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManger = new LinearLayoutManager(getContext());
        testingLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManger);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelsList));
        homePageModelList.add(new HomePageModel(1, R.mipmap.stripadd, "#000000"));
        //homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day", horizontalProductScrollModelList));
        //homePageModelList.add(new HomePageModel(1, R.mipmap.stripadd, "#ff0000"));
        //homePageModelList.add(new HomePageModel(3,"Deals of thr day",horizontalProductScrollModelList));
        //homePageModelList.add(new HomePageModel(2,"Deals of thr day",horizontalProductScrollModelList));
        //homePageModelList.add(new HomePageModel(1, R.mipmap.banner, "#ffff00"));
        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        ///////////////////////

        return view;
    }


}
