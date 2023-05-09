package com.example.coffeeapp.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeapp.registers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Intent e = new Intent(MainActivity.this, EmployeeTimekeepingV.class);
        startActivity(e);
   }
}
