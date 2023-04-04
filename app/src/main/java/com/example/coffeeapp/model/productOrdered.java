package com.example.coffeeapp.model;

import com.google.gson.annotations.SerializedName;

public class productOrdered {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String  name;
    @SerializedName("salePrice")
    private  double salePrice;
    @SerializedName("quantity")
    private  int quantity;
    @SerializedName("image")
    private String image;

    public productOrdered(int id, String name, double salePrice, int quantity, String image) {
        this.id = id;
        this.name = name;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.image = image;
    }

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

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
