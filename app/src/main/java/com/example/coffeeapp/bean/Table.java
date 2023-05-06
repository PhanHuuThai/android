package com.example.coffeeapp.bean;

import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("idTable")
    String id;

    @SerializedName("nameTable")
    String name;

    @SerializedName("status")
    int status;

    public Table(String id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
