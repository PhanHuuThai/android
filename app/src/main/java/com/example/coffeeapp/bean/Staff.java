package com.example.coffeeapp.bean;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
public class Staff {
    @SerializedName("id")
    private String id;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("email")
    private String email;
    @SerializedName("dateOfBirth")
    private String dateOfBirth;
    @SerializedName("address")
    private String address;
    @SerializedName("imageEmployee")
    private String imageEmployee;
    @SerializedName("salary")
    private int salary;
    @SerializedName("workHour")
    private int workHour;

    public Staff(String id, String name, String phonenumber, String email, String dayofbirth, String address, String imageEmployee, int salary, int numberworkingday) {
        this.id = id;
        this.fullName = name;
        this.phoneNumber = phonenumber;
        this.email = email;
        this.dateOfBirth = dayofbirth;
        this.address = address;
        this.imageEmployee = imageEmployee;
        this.salary = salary;
        this.workHour = numberworkingday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayofbirth() {
        return dateOfBirth;
    }

    public void setDayofbirth(String dayofbirth) {
        this.dateOfBirth = dayofbirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageEmployee() {
        return imageEmployee;
    }

    public void setImageEmployee(String imageEmployee) {
        this.imageEmployee = imageEmployee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getWorkHour() {
        return workHour;
    }

    public void setWorkHour(int workHour) {
        this.workHour = workHour;
    }
}
