package com.example.pankajknitting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pankajknitting.adapter.recyclerAdapter;
import com.example.pankajknitting.databinding.ActivityHomeBinding;
import com.example.pankajknitting.model.recyclerModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Home extends AppCompatActivity {
    ActivityHomeBinding binding;
    String img, desc, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        RecyclerView exclusiveOffer = findViewById(R.id.ExclusiveOffer);


        // Here, we have created new array list and added data to it
        ArrayList<recyclerModel> recyclerModelArrayList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        img = snapshot.child("img").getValue(String.class);
                        desc = snapshot.getKey() + "\n" +  snapshot.child("category").getValue() ;
                        price = snapshot.child("price").getValue().toString();
                        recyclerModelArrayList.add(new recyclerModel(img, desc, price));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerModelArrayList.add(new recyclerModel(img, desc, price));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Machi Work \nChunddi", 169.00));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Net \nChunddi", 300.50));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Printing \nChunddi", 135.90));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Lagan \nChunddi", 199.99));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Dupatta \nChunddi", 12.12));


        // we are initializing our adapter class and passing our arraylist to it.
        recyclerAdapter recyclerAdapter = new recyclerAdapter(this, recyclerModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating horizontal list so we will provide orientation as horizontal
        LinearLayoutManager ExclusiveOffer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager BestSelling = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager WoolenProducts = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        exclusiveOffer.setLayoutManager(ExclusiveOffer);
        binding.BestSelling.setLayoutManager(BestSelling);
        binding.WoolenProducts.setLayoutManager(WoolenProducts);

        exclusiveOffer.setAdapter(recyclerAdapter);
        binding.BestSelling.setAdapter(recyclerAdapter);
        binding.WoolenProducts.setAdapter(recyclerAdapter);

        startActivity(new Intent(Home.this, Home.class));
//        BottomNavigationView bottomNavigationView = (BottomNavigationView)
//                findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(
//        new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.shop:
//                        break;
//                    case R.id.explore:
//                        break;
//                    case R.id.cart:
//                        startActivity(new Intent(Home.this, OrderAccepted.class));
//                        break;
//                    case R.id.favourite:
//                        break;
//                    case R.id.account:
//                        startActivity(new Intent(Home.this, Account.class));
//                        break;
//                }
//                return false;
//            }
//        });
    }
}