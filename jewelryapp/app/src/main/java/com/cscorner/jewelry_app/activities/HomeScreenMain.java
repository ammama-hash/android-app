package com.cscorner.jewelry_app.activities; // Your package name

// --- IMPORT ALL NECESSARY CLASSES ---
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.cscorner.jewelry_app.R; // R file for resources
import com.cscorner.jewelry_app.adapters.Product; // <<<< YOUR Product class
import com.cscorner.jewelry_app.adapters.RecyclerViewAdapter; // YOUR Adapter class
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

// --- YOUR ACTIVITY CLASS DEFINITION ---
public class HomeScreenMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_main);
// --- Setup for rings ---
        RecyclerView ringsRecyclerView = findViewById(R.id.ringsRecyclerView);
        ringsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Product> ringProducts = new ArrayList<>();
        ringProducts.add(new Product("Diamond Ring", "1500Rs/-", R.drawable.ring1));
        ringProducts.add(new Product("Gold Ring", "2000Rs/-", R.drawable.ring2));

        RecyclerViewAdapter ringAdapter = new RecyclerViewAdapter(ringProducts);
        ringsRecyclerView.setAdapter(ringAdapter);


        // --- Setup for Necklaces ---
        RecyclerView necklacesRecyclerView = findViewById(R.id.necklacesRecyclerView);
        necklacesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<Product> necklaceProducts = new ArrayList<>(); // <<<< Use YOUR Product class
        // Add your necklace products with their real images
        necklaceProducts.add(new Product("Pearl Necklace", "2500Rs/-", R.drawable.necklace));
        necklaceProducts.add(new Product("Silver Chain", "1800Rs/-", R.drawable.necklace1));
        necklaceProducts.add(new Product("Gold Pendant", "3000Rs/-", R.drawable.necklace2));
        necklaceProducts.add(new Product("Crystal Set", "2200Rs/-", R.drawable.necklace4));
        // Add more necklaces here...
        RecyclerViewAdapter necklaceAdapter = new RecyclerViewAdapter(necklaceProducts);
        necklacesRecyclerView.setAdapter(necklaceAdapter);


        // --- Setup for Earrings ---
        RecyclerView earringsRecyclerView = findViewById(R.id.earringsRecyclerView);
        earringsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<Product> earringProducts = new ArrayList<>(); // <<<< Use YOUR Product class
        // Add your earring products with their real images
        earringProducts.add(new Product("Stud Earrings", "1000Rs/-", R.drawable.earrings));
        earringProducts.add(new Product("Hoop Earrings", "900Rs/-", R.drawable.earrings2));
        earringProducts.add(new Product("Dangle Earrings", "700Rs/-", R.drawable.earrings3));
        earringProducts.add(new Product("Pearl Earrings", "800Rs/-", R.drawable.earrings4));
        // Add more earrings here...
        RecyclerViewAdapter earringAdapter = new RecyclerViewAdapter(earringProducts);
        earringsRecyclerView.setAdapter(earringAdapter);


        // --- Setup for Bracelets ---
        RecyclerView braceletsRecyclerView = findViewById(R.id.braceletsRecyclerView);
        braceletsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<Product> braceletProducts = new ArrayList<>(); // <<<< Use YOUR Product class
        // Add your bracelet products with their real images
        braceletProducts.add(new Product("Chain Bracelet", "800Rs/-", R.drawable.bracelet));
        braceletProducts.add(new Product("Beaded Bracelet", "1000Rs/-", R.drawable.bracelet2));
        braceletProducts.add(new Product("Charm Bracelet", "500Rs/-", R.drawable.bracelet3));
        braceletProducts.add(new Product("Cuff Bracelet", "500Rs/-", R.drawable.bracelet4));
        // Add more bracelets here...
        RecyclerViewAdapter braceletAdapter = new RecyclerViewAdapter(braceletProducts);
        braceletsRecyclerView.setAdapter(braceletAdapter);
    }
}
