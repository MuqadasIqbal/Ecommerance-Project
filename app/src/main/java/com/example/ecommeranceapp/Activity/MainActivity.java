package com.example.ecommeranceapp.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommeranceapp.AdapterClass.CategoriesAdapter;
import com.example.ecommeranceapp.AdapterClass.ProductAdapter;
import com.example.ecommeranceapp.ModelClass.Categories;
import com.example.ecommeranceapp.ModelClass.Product;
import com.example.ecommeranceapp.databinding.ActivityMainBinding;
import com.example.ecommeranceapp.utils.Constants;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
//Array &Adapter for Categories
ArrayList<Categories>categories;
CategoriesAdapter categoriesAdapter;
//Array & Adapter for Product
ProductAdapter productAdapter;
ArrayList<Product>products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        binding.searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("query",text.toString());
                startActivity(intent);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        setContentView(binding.getRoot());

        initCategories();
        initProducts();
       /* initSlider();*/
    }

    //Slider ImageShow
 /*   private void initSlider() {
       getRecentOffers();
    }
    void getRecentOffers(){
        StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_OFFERS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject MainjsonObject=new JSONObject(response);
                    if (MainjsonObject.getString("status").equals("success")){
                        JSONArray jsonArray=MainjsonObject.getJSONArray("news_infos");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject object=jsonArray.getJSONObject(i);
                            binding.carousel.addData(
                                    new CarouselItem(
                                           Constants.NEWS_IMAGE_URL+ object.getString("image"),
                                            object.getString("title")
                                    )
                            );
                        }
                        categoriesAdapter.notifyDataSetChanged();
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
*/

    //Categories Method

    void initCategories(){
            categories=new ArrayList<>();
           GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
            binding.recyclerview.setHasFixedSize(true);
           binding.recyclerview.setLayoutManager(layoutManager);
            categoriesAdapter=new CategoriesAdapter(categories,MainActivity.this);
            binding.recyclerview.setAdapter(categoriesAdapter);

            getCategories();
        }
        void getCategories(){
            StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                  try {
                      JSONObject MainjsonObject=new JSONObject(response);
                      if (MainjsonObject.getString("status").equals("success")){
                          JSONArray jsonArray=MainjsonObject.getJSONArray("categories");
                          for (int i=0;i<jsonArray.length();i++){
                              JSONObject object=jsonArray.getJSONObject(i);
                              Categories category=new Categories(
                                      object.getString("name"),
                                      Constants.CATEGORIES_IMAGE_URL +object.getString("icon"),
                                      object.getString("color"),
                                      object.getString("brief"),
                                      object.getInt("id")
                              );
                              categories.add(category);
                          }
                          categoriesAdapter.notifyDataSetChanged();
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

        //Product Method
    private void initProducts() {
        products=new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productRecyclerview.setLayoutManager(layoutManager);
        productAdapter=new ProductAdapter(this,products);
        binding.productRecyclerview.setAdapter(productAdapter);

        getRecentProducts();
    }
    void getRecentProducts(){
        String ulr=Constants.GET_PRODUCTS_URL +"?count=20";
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
