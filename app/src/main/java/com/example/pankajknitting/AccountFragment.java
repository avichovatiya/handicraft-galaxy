package com.example.pankajknitting;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pankajknitting.databinding.FragmentAccountBinding;
import com.example.pankajknitting.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class AccountFragment extends Fragment {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ArrayList<Users> users;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
//        FragmentAccountBinding binding = FragmentAccountBinding.inflate(getLayoutInflater());

        //data fetched from firebase
        users = new ArrayList<>();
        Button logOut = view.findViewById(R.id.logOut);
        TextView txtOrders = view.findViewById(R.id.txtOrders);
        TextView uName = view.findViewById(R.id.uName);
        TextView uEmail = view.findViewById(R.id.uEmail);

        uName.setText(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName());
        uEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        txtOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OrderConfirmed.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Signed out successfully", Toast.LENGTH_LONG).show();

                startActivity(new Intent(getContext(), Signin.class));
                requireActivity().finish();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}