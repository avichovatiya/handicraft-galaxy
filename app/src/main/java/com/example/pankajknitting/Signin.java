package com.example.pankajknitting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pankajknitting.databinding.ActivitySigninBinding;
import com.example.pankajknitting.model.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Signin extends AppCompatActivity {
    ActivitySigninBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog pd;
    DatabaseReference ref;
    private GoogleSignInClient googleSignInClient;
    private final int RESULT_CODE_SINGIN=65;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance(); //initializing database instance
        mAuth = FirebaseAuth.getInstance();  //initializing auth instance
        pd = new ProgressDialog(Signin.this);

        //Onclick for SignIn with phone
        binding.editTextPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                startActivity(new Intent(Signin.this, OrderConfirmed.class));
//                finish();
            }
        });

        //Google Sigin Button
        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Signin.this, MainActivity.class));
                signInM();// declaration outside oncreate method
            }
        });
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(Signin.this, MainActivity.class));
            finish();
        }

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso); //Build a GoogleSignInClient with the options specified by gso.
    }

    //when the Google signIn Button is clicked then start the signIn Intent
    private void signInM() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RESULT_CODE_SINGIN);
    }

    // onActivityResult (Here we handle the result of the Activity )
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_CODE_SINGIN) {        //just to verify the code
            //create a Task object and use GoogleSignInAccount from Intent and write a separate method to handle singIn Result.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        //we use try catch block because of Exception.
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(Signin.this,"Signed In successfully" , Toast.LENGTH_SHORT).show();

            //SignIn successful now show authentication
            FirebaseGoogleAuth(account);
            Toast.makeText(Signin.this, "Welcome "+account.getDisplayName(), Toast.LENGTH_LONG).show();

            Intent a = new Intent(Signin.this, MainActivity.class);
            startActivity(a);
            finish();
        } catch (ApiException e) {
            e.printStackTrace();
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(Signin.this,"SignIn Failed!!!",Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        //here we are checking the Authentication Credential and checking the task is successful or not and display the message based on that.
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                      Toast.makeText(Signin.this,"successful",Toast.LENGTH_LONG).show();
                    Snackbar snackbar = Snackbar.make(binding.getRoot(),"Google SignIn Successful",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    Users user=new Users();
                    user.setUserId(account.getId());
                    user.setUserName(account.getDisplayName());
                    user.setProfilepic(account.getPhotoUrl());
                    user.setMail(account.getEmail());
                    ref=database.getReference().child("Users").child(firebaseUser.getUid()); //check if node exsist if not will create
                    ref.setValue(user); // update or add value to the node

                    Toast.makeText(Signin.this, account.getDisplayName(), Toast.LENGTH_LONG).show();

                    Intent intent
                            = new Intent(Signin.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Signin.this,"Failed!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}