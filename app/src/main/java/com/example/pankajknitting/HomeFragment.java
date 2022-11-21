package com.example.pankajknitting;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pankajknitting.adapter.recyclerAdapter;
import com.example.pankajknitting.model.recyclerModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Queue;

public class HomeFragment extends Fragment {
    DatabaseReference ref;
    ArrayList<recyclerModel> recyclerModelArraylist;
    String img;
    String desc;
    String price;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ref  = FirebaseDatabase.getInstance().getReference("Products");
        RecyclerView exclusiveOffer = view.findViewById(R.id.ExclusiveOffer);
        RecyclerView bestSelling = view.findViewById(R.id.BestSelling);
        RecyclerView woolenProducts = view.findViewById(R.id.WoolenProducts);

        // Here, we have created new array list and added data to it
        ArrayList<recyclerModel> recyclerModelArrayList = new ArrayList<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        img = snapshot.child("img").getValue(String.class);
                        desc = snapshot.getKey() + "\n" +  snapshot.child("category").getValue() ;
                        price = snapshot.child("price").getValue().toString();
                        recyclerModelArrayList.add(new recyclerModel(img, desc, price));
//                                snapshot.getKey().toString() + "\n" + snapshot.child("category").getValue(),
//                                Double.parseDouble(Objects.requireNonNull(snapshot.child("price").getValue()).toString())));
                    }
//                    desc = dataSnapshot.child("Chundadi").getKey().toString() + dataSnapshot.child("Chundadi").child("category").getValue() ;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerModelArrayList.add(new recyclerModel(img , desc, price));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Machi Work \nChunddi", 169.00));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Machi Work \nChunddi", 169.00));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Net \nChunddi", 300.50));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Printing \nChunddi", 135.90));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Lagan \nChunddi", 199.99));
//        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,"Dupatta \nChunddi", 12.12));


//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
////                snapshot.getChildren();
////                recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground, snapshot.child("desc").getValue().toString(), Double.parseDouble(snapshot.child("price").getValue().toString())));
//                if(dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        recyclerModelArrayList.add(new recyclerModel(R.mipmap.ic_chuni_foreground,
//                                snapshot.getKey().toString() + "\n" + snapshot.child("category").getValue(),
//                                Double.parseDouble(Objects.requireNonNull(snapshot.child("price").getValue()).toString())));
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        // we are initializing our adapter class and passing our arraylist to it.
        recyclerAdapter recyclerAdapter = new recyclerAdapter(this.getContext(), recyclerModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating horizontal list so we will provide orientation as horizontal
        LinearLayoutManager ExclusiveOffer = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, true);
        LinearLayoutManager BestSelling = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager WoolenProducts = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, true);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        exclusiveOffer.setLayoutManager(ExclusiveOffer);
        bestSelling.setLayoutManager(BestSelling);
        woolenProducts.setLayoutManager(WoolenProducts);

        exclusiveOffer.setAdapter(recyclerAdapter);
        bestSelling.setAdapter(recyclerAdapter);
        woolenProducts.setAdapter(recyclerAdapter);

        //data fetched from firebase
//        recyclerModelArraylist = new ArrayList<>();
//        recyclerModelArraylist.add(new recyclerModel(ref.child("Khilona").child("img"),
//                ref.child("Khilona").child("desc").toString(),
//                ref.child("Khilona").child("price").toString()
//        ));
//        initializelist(inflater,container, recyclerModelArraylist);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
//
//    private void initializelist(LayoutInflater inflater, ViewGroup container, ArrayList<recyclerModel> recyclerModelArraylist) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        RecyclerView exclusiveOffer = view.findViewById(R.id.ExclusiveOffer);
//        exclusiveOffer.setHasFixedSize(true);
//        exclusiveOffer.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
//        exclusiveOffer.getLayoutManager().setMeasurementCacheEnabled(false);
//
////        ref.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for(DataSnapshot ds : snapshot.getChildren()){
////                    recyclerModelArraylist.add(new recyclerModel(R.mipmap.ic_chuni_foreground,
////                            ds.getKey()+ "\n" + ds.child("desc").getValue(),
////                            Double.parseDouble(ds.child("price").getValue().toString())));
////                }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//        recyclerAdapter recyclerAdapter = new recyclerAdapter(this.getContext(), recyclerModelArraylist);
//
//
////        ref.addChildEventListener(new ChildEventListener() {
////            @Override
////            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////                recyclerModelArraylist.add(snapshot.getValue(recyclerModel.class));
////                recyclerAdapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////                recyclerAdapter.notifyDataSetChanged();
////
////            }
////
////            @Override
////            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
////                recyclerModelArraylist.remove(snapshot.getValue(recyclerModel.class));
////                recyclerAdapter.notifyDataSetChanged();
////
////            }
////
////            @Override
////            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//        exclusiveOffer.setAdapter(recyclerAdapter);
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        Query query = FirebaseDatabase.getInstance().getReference("Products");
//        recyclerAdapter recyclerAdapter1 = new recyclerAdapter(this.getContext(), recyclerModelArraylist);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                recyclerModelArraylist.clear();
//                if (snapshot.exists()){
//                    for(DataSnapshot mysnapshot : snapshot.getChildren()){
//                        recyclerModel recyclerModel = mysnapshot.getValue(com.example.pankajknitting.model.recyclerModel.class);
//                        recyclerModelArraylist.add(recyclerModel);
//                    }
//                    recyclerAdapter1.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}