package com.example.ecommeranceapp.Activity;

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
        setContentView(binding.getRoot());

        initCategories();
        getCategories();
        initProducts();
       /* initSlider();
    }
    private void initSlider() {
        binding.carousel.addData(new CarouselItem("https://tutorials.mianasad.com/ecommerce/uploads/news/special%20offer.jpg","some caption here"));
        binding.carousel.addData(new CarouselItem("https://tutorials.mianasad.com/ecommerce/uploads/news/Announcement.jpg","some caption here"));
        binding.carousel.addData(new CarouselItem("https://tutorials.mianasad.com/ecommerce/uploads/news/special%20offer.jpg","some caption here"));
        binding.carousel.addData(new CarouselItem("https://tutorials.mianasad.com/ecommerce/uploads/news/Announcement.jpg","some caption here"));*/
    }

    void initCategories(){
            categories=new ArrayList<>();
            String url="https://tutorials.mianasad.com/ecommerce/uploads/category/1673971344864.png";
            categories.add(new Categories("Sport","https://tutorials.mianasad.com/ecommerce/uploads/category/1673971344864.png","#18ab4e","Some Description",1));
            categories.add(new Categories("Sport","https://tutorials.mianasad.com/ecommerce/uploads/category/1673971344864.png","#fb0504","Some Description",1));
            categories.add(new Categories("Sport",url,"#4186ff","Some Description",1));
            categories.add(new Categories("Sport",url,"#BF360C","Some Description",1));
            categories.add(new Categories("Sport",url,"#ff870e","Some Description",1));
            categories.add(new Categories("Sport",url,"#ff6f52","Some Description",1));

           GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
            binding.recyclerview.setHasFixedSize(true);
           binding.recyclerview.setLayoutManager(layoutManager);
            categoriesAdapter=new CategoriesAdapter(categories,MainActivity.this);
            binding.recyclerview.setAdapter(categoriesAdapter);
        }
        void getCategories(){
            StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(request);
        }

    private void initProducts() {
        products=new ArrayList<>();
        products.add(new Product("Bridal red lehnga","https://tutorials.mianasad.com/ecommerce/uploads/product/1673839815297.jpg","",12,4,12,1));
         products.add(new Product("Bridal red lehnga","https://tutorials.mianasad.com/ecommerce/uploads/product/1673839815297.jpg","",12,4,12,1));
         products.add(new Product("Bridal red lehnga","https://tutorials.mianasad.com/ecommerce/uploads/product/1673839815297.jpg","",12,4,12,1));
         products.add(new Product("Bridal red lehnga","https://tutorials.mianasad.com/ecommerce/uploads/product/1673839815297.jpg","",12,4,12,1));
         products.add(new Product("Bridal red lehnga","https://tutorials.mianasad.com/ecommerce/uploads/product/1673839815297.jpg","",12,4,12,1));
         products.add(new Product("Bridal red lehnga","https://tutorials.mianasad.com/ecommerce/uploads/product/1673839815297.jpg","",12,4,12,1));
         products.add(new Product("Bridal red lehnga","https://tutorials.mianasad.com/ecommerce/uploads/product/1673839815297.jpg","",12,4,12,1));
         products.add(new Product("Bridal red lehnga","https://tutorials.mianasad.com/ecommerce/uploads/product/1673839815297.jpg","",12,4,12,1));


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productRecyclerview.setLayoutManager(layoutManager);
        productAdapter=new ProductAdapter(this,products);
        binding.productRecyclerview.setAdapter(productAdapter);
    }
    }
