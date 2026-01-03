package com.cscorner.jewelry_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cscorner.jewelry_app.R;
// Make sure you have a "Product.java" class in this same package or imported correctly
// import com.cscorner.jewelry_app.models.Product; // Example if it's in a 'models' package

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ProductViewHolder> {

    // A list to hold YOUR product data
    private List<Product> productList; // <<<< CORRECTED: Changed to your Product class

    // Constructor for the adapter
    public RecyclerViewAdapter(List<Product> productList) { // <<<< CORRECTED
        this.productList = productList;
    }

    /**
     * This class holds the views for a single item (from your product_item.xml)
     */
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productPrice;
        Button addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productPrice = itemView.findViewById(R.id.productPrice);
            addToCartButton = itemView.findViewById(R.id.btnAddToCart);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Hardcoded image for testing
        holder.productImage.setImageResource(R.drawable.ring1);

        // Price dynamically set
        Product product = productList.get(position);
        holder.productPrice.setText(product.getPrice());
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}
