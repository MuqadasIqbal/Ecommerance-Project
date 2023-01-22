package com.example.ecommeranceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommeranceapp.AdapterClass.ProductAdapter;
import com.example.ecommeranceapp.ModelClass.Product;
import com.example.ecommeranceapp.databinding.ActivityCategoryBinding;
import com.example.ecommeranceapp.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
ActivityCategoryBinding binding;
    ProductAdapter productAdapter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        products = new ArrayList<>();
        int catid = getIntent().getIntExtra("catId", 0);
        String categoryName = getIntent().getStringExtra("categoryName");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(categoryName);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.categoryRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, products);
        binding.categoryRecycler.setAdapter(productAdapter);

        getProducts(catid);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    void getProducts(int catid){
            String ulr= Constants.GET_PRODUCTS_URL +"?category_id=" +catid;
            StringRequest request=new StringRequest(Request.Method.GET, ulr, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject MainjsonObject=new JSONObject(response);
                        if (MainjsonObject.getString("status").equals("success")){
                            JSONArray jsonArray=MainjsonObject.getJSONArray("products");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object2=jsonArray.getJSONObject(i);
                                Product product=new Product(
                                        object2.getString("name"),
                                        Constants.PRODUCTS_IMAGE_URL+ object2.getString("image"),
                                        object2.getString("status"),
                                        object2.getDouble("price"),
                                        object2.getDouble("price_discount"),
                                        object2.getInt("stock"),
                                        object2.getInt("id")
                                );
                                products.add(product);
                            }
                            productAdapter.notifyDataSetChanged();
                        }else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }
