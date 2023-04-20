package com.example.coffeeapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.R;
import com.example.coffeeapp.information_staff;
import com.example.coffeeapp.model.employeeTimekeeping;
import com.example.coffeeapp.viewmodel.employeeTimekeepingAdapter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTimekeepingV extends AppCompatActivity {
    private RecyclerView rvItems;
    private Button btnnhanvien,btndiemdanh,btnsanpham,btnthongtin,btndoanhthu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timekeeping);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        btnthongtin= findViewById(R.id.btn_thongtin);
        btnthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EmployeeTimekeepingV.this, information_staff.class);
                startActivity(intent);
            }
        });

        btnsanpham = findViewById(R.id.btn_sanpham);
        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EmployeeTimekeepingV.this, OrderByEmployee.class);
                startActivity(intent);
            }
        });
        btnnhanvien = findViewById(R.id.btn_nhanvien);
        btnnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EmployeeTimekeepingV.this, AllStaff.class);
                startActivity(intent);
            }
        });
        btndoanhthu = findViewById(R.id.btn_doanhthu);
        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EmployeeTimekeepingV.this, RevenueView.class);
                startActivity(intent);
            }
        });

        List<employeeTimekeeping> employeeTimekeepings = new ArrayList<>();
        employeeTimekeepings.add(new employeeTimekeeping("Nguyễn Đắc Đức"));
        employeeTimekeepings.add(new employeeTimekeeping("Trần Văn Hải"));


        rvItems = (RecyclerView) findViewById(R.id.rvTimeKeeping);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(new employeeTimekeepingAdapter(employeeTimekeepings));
    }
    }
