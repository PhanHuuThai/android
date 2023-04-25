package com.example.coffeeapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.R;
import com.example.coffeeapp.information_staff;
import com.example.coffeeapp.model.revenueM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RevenueView extends AppCompatActivity {
    private TextView tvVenenueDay;
    private TextView tvVenenueMonth;
    private  double sumVenenue =0;
    private EditText editTextDay;
    private EditText editTextMonth;
    private Button btnnhanvien,btndiemdanh,btnsanpham,btnthongtin,btndoanhthu;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revenue_statistics);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnthongtin= findViewById(R.id.btn_thongtin);
        btnthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, information_staff.class);
                startActivity(intent);
            }
        });

        btndiemdanh = findViewById(R.id.btn_diemdanh);
        btndiemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, EmployeeTimekeepingV.class);
                startActivity(intent);
            }
        });
        btnsanpham = findViewById(R.id.btn_sanpham);
        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, OrderByEmployee.class);
                startActivity(intent);
            }
        });
        btnnhanvien = findViewById(R.id.btn_nhanvien);
        btnnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, AllStaff.class);
                startActivity(intent);
            }
        });





        List<revenueM> revenueMList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            revenueMList.add(new revenueM(LocalDate.of(2023, 12, 5), 200000));
            revenueMList.add(new revenueM(LocalDate.of(2023, 12, 5), 200000));
            revenueMList.add(new revenueM(LocalDate.of(2023, 12, 5), 200000));
            revenueMList.add(new revenueM(LocalDate.of(2023, 12, 5), 200000));
        }
        editTextDay = (EditText) findViewById(R.id.editTextDateDay);
        for (revenueM item : revenueMList
        ) {
            System.out.println(editTextDay.getText());
            System.out.println(String.valueOf(item.getDate()));
            if(editTextDay.getText().equals(String.valueOf(item.getDate()))){
                sumVenenue += item.getRevenue();
            }

        }

        sumVenenue = 0;
//        for (revenueM item : revenueMList
//        ) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                if(editTextMonth.getText().toString().contains(item.getDate().getMonth().toString())){
//                    sumVenenue += item.getRevenue();
//                }
//            }
//
//        }

    }
}
