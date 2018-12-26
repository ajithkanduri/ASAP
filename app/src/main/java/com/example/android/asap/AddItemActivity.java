package com.example.android.asap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        final EditText itemname = findViewById(R.id.itemname);

        final EditText price = findViewById(R.id.itemprice);

        final Spinner category = findViewById(R.id.category);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Button addToMenu = findViewById(R.id.save_details);
        addToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String itemPrice = price.getText().toString();
              String name = itemname.getText().toString();
                final String cat = category.getSelectedItem().toString();
                DatabaseReference  reference = database.getReference(LoginActivity.getUserDetails().get(0)).child(cat).child(String.valueOf(Random(15,100000)));
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Name",name);
                hashMap.put("Price",itemPrice);
                reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddItemActivity.this, "Data Save Successful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private static int Random(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
