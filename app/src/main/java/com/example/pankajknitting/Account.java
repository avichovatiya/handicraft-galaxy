package com.example.pankajknitting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.pankajknitting.databinding.ActivityAccountBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class Account extends AppCompatActivity {
ActivityAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.txtOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account.this, Welcome.class));
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.shop:
                                startActivity(new Intent(Account.this, Home.class));
                                finish();
                                break;
                            case R.id.explore:
                                break;
                            case R.id.cart:
                                startActivity(new Intent(Account.this, OrderConfirmed.class));
                                finish();
                                break;
                            case R.id.favourite:
                                break;
                            case R.id.account:
                                break;
                        }
                        return false;
                    }
                });
    }
}