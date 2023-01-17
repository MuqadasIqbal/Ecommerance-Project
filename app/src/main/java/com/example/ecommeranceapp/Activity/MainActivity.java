package com.example.ecommeranceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecommeranceapp.AdapterClass.CategoriesAdapter;
import com.example.ecommeranceapp.AdapterClass.ProductAdapter;
import com.example.ecommeranceapp.ModelClass.Categories;
import com.example.ecommeranceapp.ModelClass.Product;
import com.example.ecommeranceapp.R;
import com.example.ecommeranceapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
ArrayList<Categories>categories;
CategoriesAdapter categoriesAdapter;
ProductAdapter productAdapter;
    ArrayList<Product>products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.recyclerview.setHasFixedSize(true);

        setContentView(binding.getRoot());

        initCategories();
        initProducts();
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
           binding.recyclerview.setLayoutManager(layoutManager);
            categoriesAdapter=new CategoriesAdapter(categories,MainActivity.this);
            binding.recyclerview.setAdapter(categoriesAdapter);
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


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productRecyclerview.setLayoutManager(layoutManager);
        productAdapter=new ProductAdapter(this,products);
        binding.productRecyclerview.setAdapter(productAdapter);
    }
    }
