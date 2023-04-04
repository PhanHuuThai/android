package com.example.coffeeapp.view;

//import android.support.v7.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.R;
import com.example.coffeeapp.model.foodItem;
import com.example.coffeeapp.viewmodel.foodItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderByEmployee extends AppCompatActivity {

    private RecyclerView rvItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_by_employee);

        List<foodItem> food = new ArrayList<>();
        food.add(new foodItem("Cam", 9000,2));
        food.add(new foodItem("Chanh", 10000,4));
        food.add(new foodItem("bánh mì", 15000,1));


        rvItems = (RecyclerView) findViewById(R.id.viewEmployees);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        rvItems.setLayoutManager(layoutManager);
//        rvItems.setHasFixedSize(true);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(new foodItemAdapter(food));


    }
}