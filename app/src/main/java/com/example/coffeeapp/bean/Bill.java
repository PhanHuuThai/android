package com.example.coffeeapp.bean;

public class Bill {
    private String id;
    private int price;
    private String time;

    public Bill(String id, int price, String time) {
        this.id = id;
        this.price = price;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
