package com.example.pankajknitting;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pankajknitting.adapter.recyclerAdapter;
import com.example.pankajknitting.databinding.ActivityHomeBinding;
import com.example.pankajknitting.model.recyclerModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Objects;

public class Home extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Here, we have created new array list and added data to it
        ArrayList<recyclerModel> recyclerModelArrayList = new ArrayList<recyclerModel>();
        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Khatli Work \nChunddi", 150.00));
        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Machi Work \nChunddi", 169.00));
        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Net \nChunddi", 300.50));
        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Printing \nChunddi", 135.90));
        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Lagan \nChunddi", 199.99));
        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Dupatta \nChunddi", 12.12));


        // we are initializing our adapter class and passing our arraylist to it.
        recyclerAdapter recyclerAdapter = new recyclerAdapter(this, recyclerModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager ExclusiveOffer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager BestSelling = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager WoolenProducts = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        binding.ExclusiveOffer.setLayoutManager(ExclusiveOffer);
        binding.BestSelling.setLayoutManager(BestSelling);
        binding.WoolenProducts.setLayoutManager(WoolenProducts);

        binding.ExclusiveOffer.setAdapter(recyclerAdapter);
        binding.BestSelling.setAdapter(recyclerAdapter);
        binding.WoolenProducts.setAdapter(recyclerAdapter);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.shop:

                                break;
                            case R.id.explore:

                                break;
                            case R.id.cart:

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