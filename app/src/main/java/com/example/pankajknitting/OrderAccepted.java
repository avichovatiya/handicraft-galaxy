package com.example.pankajknitting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pankajknitting.databinding.ActivityOrderAcceptedBinding;

public class OrderAccepted extends AppCompatActivity {
    ActivityOrderAcceptedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderAcceptedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getActionBar().hide();

//        binding.trackOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(OrderAccepted.this, Account.class));
//            }
//        });
//
//        binding.backToHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(OrderAccepted.this, Home.class));
//            }
//        });
    }
}