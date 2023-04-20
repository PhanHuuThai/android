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

import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.view.EmployeeTimekeepingV;
import com.example.coffeeapp.view.OrderByEmployee;
import com.example.coffeeapp.view.RevenueView;

import java.util.ArrayList;

public class AllStaff extends AppCompatActivity {
    private RecyclerView rv_staff;
    private ArrayList<Staff> staffList;
    private CustomStaff customStaff;
    private Button btnnhanvien,btndiemdanh,btnsanpham,btnthongtin,btndoanhthu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_staff);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        btnthongtin= findViewById(R.id.btn_thongtin);
        btnthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AllStaff.this,information_staff.class);
                startActivity(intent);
            }
        });

        btndiemdanh = findViewById(R.id.btn_diemdanh);
        btndiemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AllStaff.this, EmployeeTimekeepingV.class);
                startActivity(intent);
            }
        });
        btnsanpham = findViewById(R.id.btn_sanpham);
        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AllStaff.this, OrderByEmployee.class);
                startActivity(intent);
            }
        });
        btndoanhthu = findViewById(R.id.btn_doanhthu);
        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AllStaff.this, RevenueView.class);
                startActivity(intent);
            }
        });


        staffList = new ArrayList<>();
        staffList.add(new Staff(102,"Tran Van Hai","22/8/2002",384535073,"nguya","tamloc",12,70000));


        Log.d("aaaa", Integer.toString(staffList.size()));
        rv_staff = findViewById(R.id.rv_staff);

        rv_staff.setLayoutManager(new LinearLayoutManager(this));
        customStaff = new CustomStaff(staffList, AllStaff.this);
        rv_staff.setAdapter(customStaff);
    }
}