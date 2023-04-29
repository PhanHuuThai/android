package com.example.coffeeapp.bean;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
public class Staff {
    @SerializedName("id")
    private String id;
    @SerializedName("fullName")
    private String name;
    @SerializedName("phoneNumber")
    private String phonenumber;
    @SerializedName("email")
    private String email;
    @SerializedName("dateOfBirth")
    private String dayofbirth;
    @SerializedName("address")
    private String address;
    @SerializedName("userName")
    private String username;
    @SerializedName("salary")
    private int salary;
    @SerializedName("workHour")
    private int numberworkingday;

    public Staff(String id, String name, String phonenumber, String email, String dayofbirth, String address, String username, int salary, int numberworkingday) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.dayofbirth = dayofbirth;
        this.address = address;
        this.username = username;
        this.salary = salary;
        this.numberworkingday = numberworkingday;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayofbirth() {
        return dayofbirth;
    }

    public void setDayofbirth(String dayofbirth) {
        this.dayofbirth = dayofbirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getNumberworkingday() {
        return numberworkingday;
    }

    public void setNumberworkingday(int numberworkingday) {
        this.numberworkingday = numberworkingday;
    }
}
