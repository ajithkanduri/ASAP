package com.example.android.asap.ExpandableAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.asap.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


public class MyChildViewHolder extends ChildViewHolder {

    public TextView listChild;
    public TextView listPrice;
    public ImageView imageView;
    //public Button add;




    public MyChildViewHolder(View itemView) {
        super(itemView);
        listChild = (TextView) itemView.findViewById(R.id.listChild);
        listPrice = itemView.findViewById(R.id.price);
        imageView = itemView.findViewById(R.id.vrn);

    }


    public void onBind(final String name, final String price, final String parent, final String resname) {
        listChild.setText(name);
        listPrice.setText(price);
        if(parent.contains("Non"))
        {
            imageView.setImageResource(R.drawable.nonveg);
        }
        else imageView.setImageResource(R.drawable.veg);


    }


}
