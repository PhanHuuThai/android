package com.example.coffeeapp.model;

public class foodItem {
    private  String nameFood;
    private  double salePrice;
    private  double totalPrice;
    private  int quantity;

    public foodItem(String nameFood, double salePrice,int quantity) {
        this.nameFood = nameFood;
        this.salePrice = salePrice;
        this.totalPrice = quantity*salePrice;
        this.quantity = quantity;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
