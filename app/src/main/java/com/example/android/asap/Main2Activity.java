package com.example.android.asap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button login = (Button)findViewById(R.id.login);
        final Button signup = (Button)findViewById(R.id.sigup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Main2Activity.this,LoginForDeliveryBoy.class);
                startActivity(login);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(Main2Activity.this,LoginActivity.class);
                startActivity(s);
            }
        });
    }
}

