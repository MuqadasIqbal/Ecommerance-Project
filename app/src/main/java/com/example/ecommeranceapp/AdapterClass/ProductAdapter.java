package com.example.ecommeranceapp.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommeranceapp.Activity.MainActivity;
import com.example.ecommeranceapp.ModelClass.Product;
import com.example.ecommeranceapp.R;
import com.example.ecommeranceapp.databinding.ItemProductBinding;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
Context context;
ArrayList<Product>products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context=context;
        this.products=products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product=products.get(position);
        holder.binding.label.setText(product.getName());
        holder.binding.price.setText("PKR" +product.getPrice());
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemProductBinding.bind(itemView);
        }
    }
}


