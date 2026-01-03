package com.cscorner.jewelry_app.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cscorner.jewelry_app.R;
import com.cscorner.jewelry_app.adapters.CartAdapter;
import com.cscorner.jewelry_app.database.DBHelper;
import com.cscorner.jewelry_app.models.CartModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView listView;
    Button btnCheckout;
    DBHelper db;
    ArrayList<CartModel> cartList;
    CartAdapter adapter;
    String userEmail;   // ✅ Logged-in user email

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.listViewCart);
        btnCheckout = findViewById(R.id.btnCheckout);

        db = new DBHelper(this);
        cartList = new ArrayList<>();

        // ✅ Receive email from previous activity
        userEmail = getIntent().getStringExtra("email");

        loadCartItems();

        btnCheckout.setOnClickListener(v -> {
            if(cartList.isEmpty()){
                Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                // ✅ Place order (insert into orders table)
                db.placeOrder(userEmail);

                Toast.makeText(this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();

                // ✅ Go to confirmation screen
                Intent intent = new Intent(CartActivity.this, OrderConfirmationActivity.class);
                intent.putExtra("email", userEmail);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadCartItems(){
        SQLiteDatabase sq = db.getReadableDatabase();
        Cursor cursor = sq.rawQuery("SELECT * FROM cart", null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(2);   // name
                String price = cursor.getString(3);  // price

                cartList.add(new CartModel(id, name, price));
            } while(cursor.moveToNext());
        }
        cursor.close();

        adapter = new CartAdapter(this, cartList);
        listView.setAdapter(adapter);
    }
}
