package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.coffeeapp.bean.Bill;

import java.util.ArrayList;

public class AllBill extends AppCompatActivity {

    private RecyclerView rv_bill;
    private ArrayList<Bill> billList;
    private CustomBill customBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bill);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        billList = new ArrayList<>();
        billList.add(new Bill("HD01", 70000, "15.44"));
        billList.add(new Bill("HD02", 65000, "16.00"));
        billList.add(new Bill("HD03", 50000, "16.25"));
        billList.add(new Bill("HD04", 45000, "15.30"));

        Log.d("aaaa", Integer.toString(billList.size()));
        rv_bill = findViewById(R.id.rv_bill);

        rv_bill.setLayoutManager(new LinearLayoutManager(this));
        customBill = new CustomBill(billList, AllBill.this);
        rv_bill.setAdapter(customBill);
    }
}