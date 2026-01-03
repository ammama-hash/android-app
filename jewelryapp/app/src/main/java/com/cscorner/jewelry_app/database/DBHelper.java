package com.cscorner.jewelry_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.cscorner.jewelry_app.R;

import java.time.LocalDate;
import java.time.LocalTime;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "JewelryDB";
    private static final int DATABASE_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ... (your onCreate method is fine, no changes needed)
        // Users
        db.execSQL("CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT UNIQUE," +
                "password TEXT)");

        // Products
        db.execSQL("CREATE TABLE products(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price TEXT," +
                "image INTEGER)");

        // Cart (USER BASED)
        db.execSQL("CREATE TABLE cart(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_email TEXT," +
                "product_name TEXT," +
                "price TEXT," +
                "quantity INTEGER)");

        // Orders
        db.execSQL("CREATE TABLE orders(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_email TEXT," +
                "product_name TEXT," +
                "price TEXT," +
                "quantity INTEGER," +
                "order_date TEXT," +
                "order_time TEXT," +
                "status TEXT)");

        // Sample Products
        db.execSQL("INSERT INTO products VALUES(null,'Gold Ring','12000'," + R.drawable.ring1 + ")");
        db.execSQL("INSERT INTO products VALUES(null,'Necklace','18000'," + R.drawable.necklace + ")");
        db.execSQL("INSERT INTO products VALUES(null,'Bracelet','9000'," + R.drawable.bracelet + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ... (your onUpgrade method is fine, no changes needed)
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS cart");
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }

    // ================= USERS =================

    public boolean insertUser(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);
        return db.insert("users", null, cv) != -1;
    }

    public boolean checkUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email,password});
        boolean ok = c.moveToFirst();
        c.close();
        return ok;
    }

    public boolean checkUserEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT * FROM users WHERE email=?",
                new String[]{email});
        boolean ok = c.moveToFirst();
        c.close();
        return ok;
    }

    public boolean updatePassword(String email, String newPass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPass);
        return db.update("users", cv, "email=?", new String[]{email}) > 0;
    }

    // ================= CART =================

    public void addToCart(String email, String name, String price, int qty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_email", email);
        cv.put("product_name", name);
        cv.put("price", price);
        cv.put("quantity", qty);
        db.insert("cart", null, cv);
    }

    public Cursor getCart(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM cart WHERE user_email=?",
                new String[]{email});
    }

    public void clearCart(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM cart WHERE user_email=?", new String[]{email});
    }

    // NEW, CORRECTED METHOD
    public boolean isProductInCart(String email, String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM cart WHERE user_email=? AND product_name=?",
                new String[]{email, productName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // ================= ORDER =================

    public void placeOrder(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        // Corrected query to only select items for the specific user
        Cursor c =  db.rawQuery("SELECT * FROM cart WHERE user_email=?", new String[]{email});

        while(c.moveToNext()){
            ContentValues cv = new ContentValues();
            cv.put("user_email", email);
            cv.put("product_name", c.getString(2));
            cv.put("price", c.getString(3));
            cv.put("quantity", c.getInt(4));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                cv.put("order_date", LocalDate.now().toString());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                cv.put("order_time", LocalTime.now().toString());
            }
            cv.put("status", "Placed");
            db.insert("orders", null, cv);
        }
        c.close();
        // Corrected to only delete items for the specific user
        db.execSQL("DELETE FROM cart WHERE user_email=?", new String[]{email});
    }
}
