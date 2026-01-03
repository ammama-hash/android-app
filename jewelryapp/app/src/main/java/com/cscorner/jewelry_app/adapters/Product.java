package com.cscorner.jewelry_app.adapters;


import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class Product {
    private String name;
    private String price;
    private int imageResId;

    public Product(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    // --- Getters ---
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
