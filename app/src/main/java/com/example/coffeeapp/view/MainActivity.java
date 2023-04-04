package com.example.coffeeapp.view;


import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.coffeeapp.AllBill;
import com.example.coffeeapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_remove_food);
//        Intent e = new Intent(MainActivity.this, OrderByEmployee.class);
//        startActivity(e);
    }
}
