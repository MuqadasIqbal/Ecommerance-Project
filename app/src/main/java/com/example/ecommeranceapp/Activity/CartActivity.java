package com.example.ecommeranceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecommeranceapp.AdapterClass.CartAdapter;
import com.example.ecommeranceapp.ModelClass.Product;
import com.example.ecommeranceapp.databinding.ActivityCartBinding;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
ActivityCartBinding binding;
CartAdapter adapter;
ArrayList<Product>products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        products=new ArrayList<>();
        Cart cart = TinyCartHelper.getCart();
        for (Map.Entry<Item,Integer>item:cart.getAllItemsWithQty().entrySet()){
            Product product=(Product) item.getKey();
            int quantity=item.getValue();
            product.setQuantity(quantity);
            products.add(product);
        }
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
        binding.recyclerView2.setLayoutManager(layoutManager);
        binding.recyclerView2.addItemDecoration(itemDecoration);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(CartActivity.this,CheckoutActivity.class));
            }
        });
        adapter=new CartAdapter(this, products, new CartAdapter.CartListener() {
            @Override
            public void onQuantityChanged() {
                binding.subtotal.setText(String.format("PKR %.2f",cart.getTotalPrice()));
            }
        });
        binding.recyclerView2.setAdapter(adapter);

        binding.subtotal.setText(String.format("PKR %.2f",cart.getTotalPrice()));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}