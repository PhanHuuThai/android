package com.example.coffeeapp.model;
import com.google.gson.annotations.SerializedName;
public class account {
    @SerializedName("userName")
    String userName;
    @SerializedName("passWord")
    String passWord;
    @SerializedName("role")
    int role;

    public account(String userName, String passWord, int role) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "account{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", role=" + role +
                '}';
    }
}
