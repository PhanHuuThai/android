package com.example.coffeeapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.view.EmployeeTimekeepingV;
import com.example.coffeeapp.view.Login;
import com.example.coffeeapp.view.OrderByEmployee;
import com.example.coffeeapp.view.RevenueView;
import com.example.coffeeapp.viewmodel.StaffApiService;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class information_staff extends Activity {
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    private SharedPreferences sharedpreferences;
    private String userName;
    private StaffApiService apiService;
    private Button btnnhanvien, btndiemdanh, btnsanpham, btnthongtin, btndoanhthu, btnLogout;
    private EditText id, name, date, phone, email, adress;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_staff);
        btnnhanvien = findViewById(R.id.btn_nhanvien);
        btnnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(information_staff.this, AllStaff.class);
                startActivity(intent);
            }
        });
        btndiemdanh = findViewById(R.id.btn_diemdanh);
        btndiemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(information_staff.this, EmployeeTimekeepingV.class);
                startActivity(intent);
            }
        });
        btnsanpham = findViewById(R.id.btn_sanpham);
        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(information_staff.this, OrderByEmployee.class);
                startActivity(intent);
            }
        });
        btndoanhthu = findViewById(R.id.btn_doanhthu);
        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(information_staff.this, RevenueView.class);
                startActivity(intent);
            }
        });
        id = findViewById(R.id.edit_ID);
        name = findViewById(R.id.edit_Name);
        date = findViewById(R.id.edit_DateBirth);
        phone = findViewById(R.id.edit_phone);
        email = findViewById(R.id.edit_Mail);
        adress = findViewById(R.id.edit_IDCard);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userName = sharedpreferences.getString(EMAIL_KEY,null);
        Log.d("DEBUG",userName);
        apiService = new StaffApiService();
        apiService.GetAllStaff(userName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Staff>>() {
                    @Override
                    public void onSuccess(@NonNull List<Staff> staff) {
                        for (Staff item:staff){
                            id.setText(String.valueOf(item.getId()));
                            name.setText(item.getName());
                            date.setText(item.getDayofbirth());
                            phone.setText(item.getPhonenumber());
                            email.setText(item.getEmail());
                            adress.setText(item.getAddress());
                            Log.d("DEBUG1",item.toString());
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(information_staff.this, "Không có thông tin !", Toast.LENGTH_SHORT).show();
                    }
                });

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(information_staff.this, Login.class);
                startActivity(intent);
            }
        });
    }
}