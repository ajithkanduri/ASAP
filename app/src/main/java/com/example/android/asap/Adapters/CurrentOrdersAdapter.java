package com.example.android.asap.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.android.asap.Model.Order;
import com.example.android.asap.Model.cartOrder;
import com.example.android.asap.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentOrdersAdapter extends RecyclerView.Adapter<CurrentOrdersAdapter.CurrentOrdersViewHolder> implements View.OnClickListener {
    ArrayList<cartOrder> currentOrders;
    Context context;
    public CurrentOrdersAdapter(final ArrayList<cartOrder> currentOrders,Context context)
    {
       this.currentOrders = currentOrders;
       this.context = context;
        System.out.println("In Adapater");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("VantilluRestaurantOrders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    cartOrder cartOrder = dataSnapshot1.getValue(cartOrder.class);
                    currentOrders.add(dataSnapshot1.getValue(cartOrder.class));
                    notifyDataSetChanged();
                    System.out.println(cartOrder.getDeliveryAddress());
                    System.out.println(cartOrder.getOrder());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @NonNull
    @Override
    public CurrentOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout,parent,false);
        CurrentOrdersViewHolder currentOrdersViewHolder = new CurrentOrdersViewHolder(v);
        return currentOrdersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CurrentOrdersViewHolder holder, final int position) {
        holder.txtOrderId.setText(currentOrders.get(position).getId());
        holder.txtOrderPhone.setText(currentOrders.get(position).getPhnNumber());
        holder.txtOrderAddress.setText(currentOrders.get(position).getDeliveryAddress());
        holder.txtOrderStatus.setText(currentOrders.get(position).getStatus());
        ArrayList<Order> itemList = currentOrders.get(position).getOrder();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<itemList.size();i++)
        {
            stringBuffer.append(itemList.get(i).getItem()+" : "+itemList.get(i).getQuantity()+"\n");
        }
        stringBuffer.append("Restaurant Total Bill : "+currentOrders.get(position).getResTotal());
        holder.txtOrdersItems.setText(String.valueOf(stringBuffer));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Trying to open PopUpMenu");
                createAlertDialog(currentOrders.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return currentOrders.size();
    }
    public void createAlertDialog(final String id)
    {
        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(context).setTitle("Update The Info.");
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.dialog_feed,null);
        builder.setView(v);
        AlertDialog dialog=builder.create();
        dialog.show();
        CheckBox received = v.findViewById(R.id.checkBox);
        received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ((CheckBox)v).isChecked() ) {
                    System.out.println("Delivered is checked\n\n");
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("VantilluRestaurantOrders");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                cartOrder cartOrder = dataSnapshot1.getValue(com.example.android.asap.Model.cartOrder.class);
                                if(cartOrder.getId().equals(id))
                                {
                                    DatabaseReference reference = dataSnapshot1.getRef().child("status");
                                    reference.setValue("Order Received!!");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        CheckBox delivered = v.findViewById(R.id.checkBox2);
        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ((CheckBox)v).isChecked() ) {
                    System.out.println("Delivered is checked\n\n");
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("VantilluRestaurantOrders");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                cartOrder cartOrder = dataSnapshot1.getValue(com.example.android.asap.Model.cartOrder.class);
                                if(cartOrder.getId().equals(id))
                                {
                                    DatabaseReference reference = dataSnapshot1.getRef().child("status");
                                    reference.setValue("Out For Delivery!!");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        CheckBox delete = v.findViewById(R.id.checkBox4);
        if(delete.isChecked())
        {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("VantilluRestaurantOrders").child(id);
            databaseReference.removeValue();
            //received.setText("Delivered To Delivery Boy");
        }

    }
    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(context,view);
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

    public class CurrentOrdersViewHolder extends RecyclerView.ViewHolder  {
        private TextView txtOrderId;
        private TextView txtOrderAddress;
        private TextView txtOrderStatus;
        private TextView txtOrderPhone;
        private TextView txtOrdersItems;
        public CurrentOrdersViewHolder(View itemView) {
            super(itemView);
            txtOrderId = itemView.findViewById(R.id.orderId);
            txtOrderAddress = itemView.findViewById(R.id.orderAddress);
            txtOrderPhone = itemView.findViewById(R.id.orderPhone);
            txtOrderStatus = itemView.findViewById(R.id.orderStatus);
            txtOrdersItems = itemView.findViewById(R.id.orderItems);
        }


    }
}
