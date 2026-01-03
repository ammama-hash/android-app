package com.cscorner.jewelry_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.cscorner.jewelry_app.R;
import com.cscorner.jewelry_app.models.CartModel;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    ArrayList<CartModel> list;
    LayoutInflater inflater;

    public CartAdapter(Context c, ArrayList<CartModel> list){
        this.context = c;
        this.list = list;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int i) { return list.get(i); }

    @Override
    public long getItemId(int i) { return list.get(i).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_cart, parent, false);
        }

        TextView name = convertView.findViewById(R.id.productImage);
        TextView price = convertView.findViewById(R.id.productPrice);
        TextView quantity = convertView.findViewById(R.id.Quantity); // add in XML

        CartModel c = list.get(position);
        name.setText(c.getName());
        quantity.setText("Qty: " + c.getQuantity());
        price.setText("₹ " + c.getSubtotal());

        return convertView;
    }
}
