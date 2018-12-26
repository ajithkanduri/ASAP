package com.example.android.asap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.asap.ExpandableAdapter.ChildList;
import com.example.android.asap.ExpandableAdapter.DocExpandableRecyclerAdapter;
import com.example.android.asap.ExpandableAdapter.ParentList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends AppCompatActivity {
    private Menu collapsedMenu;
    private boolean appBarExpanded = true;
    // PeopleAdapter adapter;
    DocExpandableRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent i = getIntent();
        final String name = i.getStringExtra("Name");
        String image = i.getStringExtra("Image");
        ImageView imageView = (ImageView)findViewById(R.id.header);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);



        Glide.with(this).load(image).apply(options).into( imageView);



                final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        //toolbar.setBackgroundColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(name);
        collapsingToolbar.setBackgroundColor(Color.parseColor("#FFFFFF"));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(MenuActivity.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 200) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                }
            }
        });
        final RecyclerView recyclerView = findViewById(R.id.menu_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new PeopleAdapter(this,name);
//        adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
//
//        recyclerView.setAdapter(adapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Reference to your entire Firebase database
        DatabaseReference parentReference = database.getReference(name).child("Menu");

        //reading data from firebase
        parentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<ParentList> Parent = new ArrayList<>();
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()){


                    final String ParentKey = snapshot.getKey().toString();

                    snapshot.child("titre").getValue();

                    DatabaseReference childReference =
                            FirebaseDatabase.getInstance().getReference(name).child("Menu").child(ParentKey);
                    childReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final List<ChildList> Child = new ArrayList<>();


                            for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                                final String ChildValue = snapshot1.child("Name").getValue(String.class);
                                final String price = String.valueOf(snapshot1.child("Price").getValue(String.class));

                                System.out.println(ChildValue);

                                Child.add(new ChildList(ChildValue,price,ParentKey,name));

                            }Parent.add(new ParentList(ParentKey, Child));

                            adapter = new DocExpandableRecyclerAdapter(Parent);
                            recyclerView.setAdapter(adapter);
                            System.out.println(ParentKey+Child);}

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }}



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public static int random() {
        int randomNum;
        randomNum =(int)(Math.random());

        return randomNum;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }

}

