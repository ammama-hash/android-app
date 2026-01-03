package com.cscorner.jewelry_app.models;

public class ProductModel {

    private int id;
    private String name;
    private String price;
    private int image;

    // Constructor with all fields
    public ProductModel(int id, String name, String price, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    // Optional: default constructor
    public ProductModel() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setQuantity(int quantity) {
        this.id = quantity;
    }
}
