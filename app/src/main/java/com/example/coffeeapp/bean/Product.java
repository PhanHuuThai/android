package com.example.coffeeapp.bean;

public class Product
{
    private String Name ;
    private int Price ;
    private String SourceImage ;
    private int Amount ;

    public Product(String name, int price, String sourceImage, int amount) {
        Name = name;
        Price = price;
        SourceImage = sourceImage;
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getSourceImage() {
        return SourceImage;
    }

    public void setSourceImage(String sourceImage) {
        SourceImage = sourceImage;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
