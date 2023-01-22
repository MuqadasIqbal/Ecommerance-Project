package com.example.ecommeranceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ecommeranceapp.databinding.ActivityPaymentBinding;
import com.example.ecommeranceapp.utils.Constants;

public class PaymentActivity extends AppCompatActivity {
ActivityPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String orderCode=getIntent().getStringExtra("orderCode");
        binding.webview.setMixedContentAllowed(true);
        binding.webview.loadUrl(Constants.PAYMENT_URL+ orderCode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}