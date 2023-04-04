package com.example.coffeeapp.bean;

public class StaffDetail {
    private int id;
    private String name;
    private String dayofbirth;
    private int phonenumber;
    private String email;
    private String address;
    private int numberworkingday;
    private int quantity;

    public StaffDetail(int id, String name, String dayofbirth, int phonenumber, String email, String address, int numberworkingday, int quantity) {
        this.id = id;
        this.name = name;
        this.dayofbirth = dayofbirth;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.numberworkingday = numberworkingday;
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

    public String getDayofbirth() {
        return dayofbirth;
    }

    public void setDayofbirth(String dayofbirth) {
        this.dayofbirth = dayofbirth;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberworkingday() {
        return numberworkingday;
    }

    public void setNumberworkingday(int numberworkingday) {
        this.numberworkingday = numberworkingday;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
