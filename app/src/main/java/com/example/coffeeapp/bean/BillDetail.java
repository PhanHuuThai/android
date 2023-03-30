package com.example.coffeeapp.bean;

public class BillDetail {
    private int id;
    private String name;
    private String size;
    private int quantity;
    private int price;

    public BillDetail(int id, String name, String size, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
