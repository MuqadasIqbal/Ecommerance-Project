package com.example.ecommeranceapp.AdapterClass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommeranceapp.ModelClass.Categories;
import com.example.ecommeranceapp.R;
import com.example.ecommeranceapp.databinding.ItemCategoriesBinding;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    ArrayList<Categories> categories;
    Context context;


    public CategoriesAdapter(ArrayList<Categories> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_categories,parent,false);
        CategoryViewHolder viewHolder=new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Categories category=categories.get(position);
        holder.binding.label.setText(category.getName());
        holder.binding.image.setBackgroundColor(Color.parseColor(category.getColor()));
        Glide.with(context)
                .load(category.getIcon())
                .into(holder.binding.image);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ItemCategoriesBinding binding;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemCategoriesBinding.bind(itemView);
        }
    }

}