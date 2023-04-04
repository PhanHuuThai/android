package com.example.coffeeapp;

//import android.support.v7.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeapp.R;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         //setContentView(R.layout.thongtincanhan);
       Intent e = new Intent(MainActivity.this, AllStaff.class);
        startActivity(e);
   }
}