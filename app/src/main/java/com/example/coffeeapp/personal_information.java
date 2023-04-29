package com.example.coffeeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class personal_information extends Activity {
    private EditText id,name,date,phone,email,adress,salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        Intent in = getIntent();
        id= findViewById(R.id.edit_ID);
        name = findViewById(R.id.edit_Name);
        date = findViewById(R.id.edit_DateBirth);
        phone = findViewById(R.id.edit_phone);
        email = findViewById(R.id.edit_Mail);
        adress = findViewById(R.id.edit_IDdiachi);
        salary = findViewById(R.id.edit_luong);
        Log.d("DEBUG","personal_information");
        id.setText(in.getStringExtra("id"));
        name.setText(in.getStringExtra("Fullname"));
        date.setText(in.getStringExtra("Dayofbirth"));
        phone.setText(in.getStringExtra("Phonenumber"));
        email.setText(in.getStringExtra("email"));
        adress.setText(in.getStringExtra("Address"));
        salary.setText(String.valueOf(in.getIntExtra("Salary",0)*in.getIntExtra("Numberworkingday",0)));
        Log.d("DEBUG","personal_information_2");

    }
}