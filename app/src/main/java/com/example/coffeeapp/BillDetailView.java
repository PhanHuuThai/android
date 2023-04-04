package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.coffeeapp.bean.BillDetail;

import java.util.ArrayList;

public class BillDetailView extends AppCompatActivity {

    private RecyclerView rv_billdetail;
    private ArrayList<BillDetail> billDetails;
    private CustomBillDetail customBillDetail;
    private Button btn_Thoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        billDetails = new ArrayList<>();

        billDetails.add(new com.example.coffeeapp.bean.BillDetail(0,"cà phê sữa", "M", 1, 25000));
        billDetails.add(new com.example.coffeeapp.bean.BillDetail(2,"Trà sữa", "L", 2, 30000));
        billDetails.add(new com.example.coffeeapp.bean.BillDetail(3,"Trà chanh", "M", 1, 20000));

        rv_billdetail = findViewById(R.id.rv_billdetail);
        btn_Thoat = findViewById(R.id.btn_thoat);

        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(BillDetailView.this, AllBill.class);
                startActivity(e);
            }
        });

        rv_billdetail.setLayoutManager(new LinearLayoutManager(this));
        customBillDetail = new CustomBillDetail(billDetails);
        rv_billdetail.setAdapter(customBillDetail);
    }
}