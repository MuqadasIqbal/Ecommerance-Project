package com.example.ecommeranceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ecommeranceapp.ModelClass.Product;
import com.example.ecommeranceapp.R;
import com.example.ecommeranceapp.databinding.ActivityProductDetailBinding;
import com.example.ecommeranceapp.utils.Constants;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailActivity extends AppCompatActivity {
ActivityProductDetailBinding binding;
Product currentProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name=getIntent().getStringExtra("name");
         String image=getIntent().getStringExtra("image");
         int id=getIntent().getIntExtra("id",0);
         double price=getIntent().getDoubleExtra("price",0);

        Glide.with(this)
                .load(image)
                .into(binding.productImage);

        getProductDetails(id);

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Cart cart = TinyCartHelper.getCart();
        binding.AddtoCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.addItem(currentProduct,20);
                binding.AddtoCardBtn.setEnabled(false);
                binding.AddtoCardBtn.setText("Add to Cart");
                Toast.makeText(ProductDetailActivity.this, "Product add to Cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.cart){
            startActivity(new Intent(this,CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    void  getProductDetails(int id){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url= Constants.GET_PRODUCT_DETAILS_URL+id;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getString("status").equals("success")){
                        JSONObject object=jsonObject.getJSONObject("product");
                        String description=object.getString("description");
                        binding.productDescription.setText(
                                Html.fromHtml(description)
                        );
                        currentProduct=new Product(
                                object.getString("name"),
                                Constants.PRODUCTS_IMAGE_URL+ object.getString("image"),
                                object.getString("status"),
                                object.getDouble("price"),
                                object.getDouble("price_discount"),
                                object.getInt("stock"),
                                object.getInt("id")
                        );
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
        requestQueue.add(request);

    }
}