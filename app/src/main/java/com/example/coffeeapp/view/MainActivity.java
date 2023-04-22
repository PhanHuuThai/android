package com.example.coffeeapp.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.MenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Intent e = new Intent(MainActivity.this, Login.class);
        startActivity(e);
   }
}
