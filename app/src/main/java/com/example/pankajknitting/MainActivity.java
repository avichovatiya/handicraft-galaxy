package com.example.pankajknitting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import java.io.*;
import com.example.pankajknitting.adapter.recyclerAdapter;
import com.example.pankajknitting.databinding.ActivityHomeBinding;
import com.example.pankajknitting.model.recyclerModel;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.shop);

    }

    HomeFragment HomeFragment = new HomeFragment();
    AccountFragment AccountFragment = new AccountFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.shop:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, HomeFragment).commit();
                return true;

            case R.id.account:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, AccountFragment).commit();
                return true;
        }
        return false;
    }
}