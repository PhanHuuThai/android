package com.example.coffeeapp.view;


import android.content.Intent;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeapp.AllBill;
import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.AllTable;
import com.example.coffeeapp.CustomStaff;
import com.example.coffeeapp.ListDrink;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         //setContentView(R.layout.thongtincanhan);
       Intent e = new Intent(MainActivity.this, AllStaff.class);
        startActivity(e);
   }

}
