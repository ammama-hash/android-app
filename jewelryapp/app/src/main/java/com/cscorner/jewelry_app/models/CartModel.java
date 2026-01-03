package com.cscorner.jewelry_app.models;

public class CartModel {
    private int id;
    private String name, price;
    private int quantity;
    private int subtotal;

    public CartModel(int id, String name, String price){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getSubtotal() { return subtotal; }
}
