package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.view.EmployeeTimekeepingV;
import com.example.coffeeapp.view.OrderByEmployee;
import com.example.coffeeapp.view.RevenueView;
import com.example.coffeeapp.viewmodel.ProducctOrderAdapter;
import com.example.coffeeapp.viewmodel.ProductOrderApiService;
import com.example.coffeeapp.viewmodel.StaffAdapter;
import com.example.coffeeapp.viewmodel.StaffApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllStaff extends AppCompatActivity {
    private RecyclerView rv_staff;
    private ArrayList<Staff> staffList;
    private StaffApiService staffService;
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
        staffService = new StaffApiService();
        List<Staff> list = new ArrayList<>();
        rv_staff = findViewById(R.id.rv_staff);
        StaffAdapter staffAdapter = new StaffAdapter(list,this);
        rv_staff.setAdapter(staffAdapter);
        rv_staff.setLayoutManager(new LinearLayoutManager(this));
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
                            staffAdapter.notifyDataSetChanged();
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