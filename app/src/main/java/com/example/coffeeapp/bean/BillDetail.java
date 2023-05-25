package com.example.coffeeapp.bean;

import com.google.gson.annotations.SerializedName;

public class BillDetail {
    @SerializedName("idOrderDetail")
    private String idOrderDetail;

    @SerializedName("idProduct")
    private String idProduct;

    @SerializedName("idOrder")
    private String idOrder;

    @SerializedName("count")
    private int quantity;

    @SerializedName("note")
    private String note;

    public BillDetail(String idOrderDetail, String idProduct, String idOrder, int quantity, String note) {
        this.idOrderDetail = idOrderDetail;
        this.idProduct = idProduct;
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.note = note;
    }

    public String getIdOrderDetail() {
        return idOrderDetail;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNote() {
        return note;
    }

    public void setIdOrderDetail(String idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "BillDetail{" +
                "idOrderDetail='" + idOrderDetail + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", idOrder='" + idOrder + '\'' +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                '}';
    }
}
