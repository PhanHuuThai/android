package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.coffeeapp.Been.Staff;

import java.util.ArrayList;

public class personal_information extends Activity {
    private EditText id,name,date,phone,email,adress,salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        id= findViewById(R.id.edit_ID);
        name = findViewById(R.id.edit_Name);
        date = findViewById(R.id.edit_DateBirth);
        phone = findViewById(R.id.edit_phone);
        email = findViewById(R.id.edit_Mail);
        adress = findViewById(R.id.edit_IDdiachi);
        salary = findViewById(R.id.edit_luong);

    }
}