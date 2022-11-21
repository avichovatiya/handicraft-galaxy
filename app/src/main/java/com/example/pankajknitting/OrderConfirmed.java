package com.example.pankajknitting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pankajknitting.databinding.ActivityOrderConfirmedBinding;

public class OrderConfirmed extends AppCompatActivity {
    ActivityOrderConfirmedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderConfirmedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.trackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderConfirmed.this, MainActivity.class));
            }
        });

        binding.backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.mainContent, fragment).commit();
            }
        });



//        binding.trackOrder
    }
}