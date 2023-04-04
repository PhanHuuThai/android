package com.example.coffeeapp.model;

public class Product {
    private int id;
    private String name;
    private double salePrice;
    private String image;
    private int quantity;

    public Product(int id, String name, double salePrice, int quantity, String image) {
        this.id = id;
        this.name = name;
        this.salePrice = salePrice;
        this.image = image;
        this.quantity = quantity;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
