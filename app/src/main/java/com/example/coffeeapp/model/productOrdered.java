package com.example.coffeeapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class productOrdered implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String  name;
    @SerializedName("salePrice")
    private  double salePrice;
    @SerializedName("quantity")
    private  int quantity;
    @SerializedName("image")
    private String image;
    @SerializedName("idCatelory")
    private  String idCategory;

    @Override
    public String toString() {
        return "productOrdered{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salePrice=" + salePrice +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", idCategory='" + idCategory + '\'' +
                '}';
    }

    public productOrdered(String id, String name, double salePrice, int quantity, String image, String idCategory) {
        this.id = id;
        this.name = name;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.image = image;
        this.idCategory= idCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }
}
