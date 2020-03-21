package com.example.shopitee;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopitee.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder viewHoldere, int postion) {
        String icon = categoryModelList.get(postion).getCategoryIconLink();
        String name = categoryModelList.get(postion).getCategoryName();
        viewHoldere.setCategory(name,postion);


    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
    public class  ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);
        }

        private void setCategoryIcon (){

            //todo: set categoryIcons here;

        }
        private void setCategory(final String name, final int position){
            categoryName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position != 0) {
                        Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                        categoryIntent.putExtra("CategoryName", name);
                        itemView.getContext().startActivity(categoryIntent);
                    }

                }
            });
        }

    }
}
