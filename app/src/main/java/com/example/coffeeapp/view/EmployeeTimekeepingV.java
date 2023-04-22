package com.example.coffeeapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.R;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.information_staff;
import com.example.coffeeapp.model.employeeTimekeeping;
import com.example.coffeeapp.viewmodel.StaffAdapter;
import com.example.coffeeapp.viewmodel.StaffApiService;
import com.example.coffeeapp.viewmodel.employeeTimekeepingAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EmployeeTimekeepingV extends AppCompatActivity {
    private RecyclerView rvItems;
    private ArrayList<Staff> staffList;
    private StaffApiService staffService;
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
        staffService = new StaffApiService();
        List<Staff> list = new ArrayList<>();
        rvItems = (RecyclerView) findViewById(R.id.rvTimeKeeping);
        employeeTimekeepingAdapter employeeTimekeepingAdapter = new employeeTimekeepingAdapter(list);
        rvItems.setAdapter(employeeTimekeepingAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        staffService.GetAllStaff("All")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Staff>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(@NonNull List<Staff> staffs) {
                        Log.d("DEBUG","Success");
                        for (Staff item :staffs
                        ) {
                            Log.d("DEBUG"," "+item.getName());
                            list.add(item);
                            employeeTimekeepingAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG","Fail "+e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
    }
