package com.example.android.asap;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.asap.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public static ArrayList<String> userDetails=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText) findViewById(R.id.logusr);
        final EditText pass = (EditText)findViewById(R.id.logpswrd);
        Button login = (Button)findViewById(R.id.login);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_dBoy = database.getReference("Delivery Boy");
        final DatabaseReference table_resOwner = database.getReference("Restaurant Owner");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Loading...");
                mDialog.show();
                System.out.println("41qwertyuiopsdfghjklcvbnm,.");
                    table_resOwner.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            System.out.println("45qwertyuiopsdfghjklcvbnm,.");
                            if (dataSnapshot.child(username.getText().toString()).exists()) {
                                System.out.println("47qwertyuiopsdfghjklcvbnm,.");
                                User user = dataSnapshot.child(username.getText().toString()).getValue(User.class);
                                if (user.getPassword().equals(pass.getText().toString())) {
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent login = new Intent(LoginActivity.this, RestaurantOwner.class);

                                    userDetails.add(user.getUsername());
                                    userDetails.add(user.getPassword());
                                    userDetails.add(username.getText().toString());
                                    for (int i = 0; i < userDetails.size(); i++) {
                                        System.out.println(userDetails.get(i));
                                    }
                                    mDialog.dismiss();
                                    startActivity(login);
                                    finish();
                                } else {
                                    mDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Not yet registered", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    }
        });

    }

    public static ArrayList<String> getUserDetails() {
        return userDetails;
    }
}


