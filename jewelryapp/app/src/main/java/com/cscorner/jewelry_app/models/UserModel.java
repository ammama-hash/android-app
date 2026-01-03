package com.cscorner.jewelry_app.models;

public class UserModel {
    private int id;
    private String name, email, password;

    public UserModel(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
}
