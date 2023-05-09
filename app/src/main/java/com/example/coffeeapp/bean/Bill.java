package com.example.coffeeapp.bean;

import com.google.gson.annotations.SerializedName;

public class Bill {

    @SerializedName("idOrder")
    private String id;

    @SerializedName("dateOrder")
    private String time;

    @SerializedName("idEmployee")
    private String idEmployee;

    @SerializedName("priceTotal")
    private Double price;

    @SerializedName("idTable")
    private String idTable;

    public Bill(String id, String time, String idEmployee, Double price, String idTable) {
        this.id = id;
        this.time = time;
        this.idEmployee = idEmployee;
        this.price = price;
        this.idTable = idTable;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public Double getPrice() {
        return price;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    @Override
    public String toString() {
        return "Bill :" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", idEmployee='" + idEmployee + '\'' +
                ", price=" + price +
                ", idTable='" + idTable ;
    }
}
