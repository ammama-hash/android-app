package com.cscorner.jewelry_app.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.*;
import android.widget.*;
import com.cscorner.jewelry_app.R;
import com.cscorner.jewelry_app.database.DBHelper;
import com.cscorner.jewelry_app.models.ProductModel;
import java.util.ArrayList;

public class ProductGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ProductModel> products;
    private DBHelper db;
    private LayoutInflater inflater;

    public ProductGridAdapter(Context context, ArrayList<ProductModel> products, DBHelper db){
        this.context = context;
        this.products = products;
        this.db = db;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){ return products.size(); }
    @Override
    public Object getItem(int i){ return products.get(i); }
    @Override
    public long getItemId(int i){ return products.get(i).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = inflater.inflate(R.layout.activity_cart, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.productImage);
        TextView name = convertView.findViewById(R.id.productName);
        TextView price = convertView.findViewById(R.id.productPrice);
        Button addToCart = convertView.findViewById(R.id.btnAddToCart);

        ProductModel p = products.get(position);
        img.setImageResource(p.getImage());
        name.setText(p.getName());
        price.setText("₹ " + p.getPrice());

        addToCart.setOnClickListener(v -> {
            boolean inserted = db.getCart(p.getName());
            if(inserted) {
                Toast.makeText(context, p.getName()+" added to cart", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context,"Error adding to cart", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
