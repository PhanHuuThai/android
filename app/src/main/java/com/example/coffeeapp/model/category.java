package com.example.coffeeapp.model;

import com.google.gson.annotations.SerializedName;

public class category {
    @SerializedName("idCategory")
    private String idCategory;
    @SerializedName("nameCategory")
    private String name;

    public category(String idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
