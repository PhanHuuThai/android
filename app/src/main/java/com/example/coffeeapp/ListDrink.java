package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.coffeeapp.bean.BillDetail;

import java.util.ArrayList;

public class ListDrink extends AppCompatActivity {
    private RecyclerView rv_listdrink;
    private ArrayList<BillDetail> listDrink;
    private CustomBillDetail customBillDetail;
    private ImageView imgcoffee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drink);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        imgcoffee = findViewById(R.id.imgcafe);
        imgcoffee.setImageResource(R.drawable.icon_coffee);

        listDrink = new ArrayList<>();

        listDrink.add(new BillDetail(0,"cà phê sữa", "M", 1, 25000));
        listDrink.add(new BillDetail(2,"Trà sữa", "L", 2, 30000));
        listDrink.add(new BillDetail(3,"Trà chanh", "M", 1, 20000));

        Log.d("aaaa", Integer.toString(listDrink.size()));
        rv_listdrink = findViewById(R.id.rv_listdrink);

        rv_listdrink.setLayoutManager(new LinearLayoutManager(this));
        customBillDetail = new CustomBillDetail(listDrink);
        rv_listdrink.setAdapter(customBillDetail);
    }
}