package com.example.shopitee.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shopitee.db.ShopiteeDatabase;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopitee.HorizontalProductScrollModel;
import com.example.shopitee.R;
import com.example.shopitee.models.ItemCartModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import es.dmoral.toasty.Toasty;
import kotlin.coroutines.experimental.Continuation;


public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> implements Filterable {

    private List<HorizontalProductScrollModel>horizontalProductScrollModelList;
    private List<HorizontalProductScrollModel>horizontalProductScrollModelListFull;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
        this.horizontalProductScrollModelListFull = new ArrayList<>(this.horizontalProductScrollModelList);
    }
    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout,viewGroup,false);
        view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewHolder, int postion) {
        int resourse = horizontalProductScrollModelList.get(postion).getProductImage();
        final String title = horizontalProductScrollModelList.get(postion).getProductTitle();
        String description = horizontalProductScrollModelList.get(postion).getProductDescription();
        final String price = horizontalProductScrollModelList.get(postion).getProductPrice();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(v.getContext(), "${currentItem.productTitle} added to cart.").show();
            }
        });
        viewHolder.setProductImage(resourse);
        viewHolder.setProductTitle(title);
        viewHolder.setProductDescription(description);
        viewHolder.setProductPrice(price);

    }

    @Override
    public int getItemCount() {
        if(horizontalProductScrollModelList.size() > 8){
            return 8;
        }else{
            return horizontalProductScrollModelList.size();
        }

    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<HorizontalProductScrollModel> filterList = new ArrayList<>();
            if(constraint == null || constraint.length() ==0){
                filterList.addAll(horizontalProductScrollModelListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (HorizontalProductScrollModel productModel :
                        horizontalProductScrollModelListFull) {
                    if (productModel.getProductTitle().toLowerCase().contains(filterPattern)){
                        filterList.add(productModel);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            horizontalProductScrollModelList.clear();
            horizontalProductScrollModelList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_decription);
            productPrice = itemView.findViewById(R.id.h_s_product_price);
        }

        private void setProductImage(int resource){
            productImage.setImageResource(resource);
        }
        private void setProductTitle(String title){
            productTitle.setText(title);
        }
        private void setProductDescription(String description){
            productDescription.setText(description);
        }
        private void setProductPrice(String price){
            productPrice.setText(price);
        }

    }
}
