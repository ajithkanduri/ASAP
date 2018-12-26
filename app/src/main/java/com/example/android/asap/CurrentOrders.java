package com.example.android.asap;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.android.asap.Adapters.CurrentOrdersAdapter;
import com.example.android.asap.Adapters.RecyclerItemClickListener;
import com.example.android.asap.Model.cartOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentOrders extends AppCompatActivity {
    ArrayList<cartOrder> currentOrders = new ArrayList<>();
    CurrentOrdersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);

        RecyclerView recyclerView = findViewById(R.id.listOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
         adapter = new CurrentOrdersAdapter(currentOrders,CurrentOrders.this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                PopupMenu popupMenu = new PopupMenu(CurrentOrders.this, view);
                popupMenu.inflate(R.menu.updatemenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.update:
                                //handle menu1 click

                                return true;
                            case R.id.delete:
                                //handle menu2 click
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        }));
       // loadOrders();
//        notify();
    }
    public void loadOrders()
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(LoginActivity.getUserDetails().get(0)).child("CurrentOrders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    cartOrder cartOrder = dataSnapshot1.getValue(com.example.android.asap.Model.cartOrder.class);
                    System.out.println(cartOrder.getDeliveryAddress());
                    System.out.println(cartOrder.getOrder());
                    currentOrders.add(dataSnapshot1.getValue(cartOrder.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
