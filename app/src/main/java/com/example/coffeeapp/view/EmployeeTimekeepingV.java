package com.example.coffeeapp.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeapp.R;
import com.example.coffeeapp.model.employeeTimekeeping;
import com.example.coffeeapp.viewmodel.employeeTimekeepingAdapter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTimekeepingV extends AppCompatActivity {
    private RecyclerView rvItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timekeeping);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        List<employeeTimekeeping> employeeTimekeepings = new ArrayList<>();
        employeeTimekeepings.add(new employeeTimekeeping("Nguyễn Đắc Đức"));
        employeeTimekeepings.add(new employeeTimekeeping("Trần Văn Hải"));


        rvItems = (RecyclerView) findViewById(R.id.rvTimeKeeping);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(new employeeTimekeepingAdapter(employeeTimekeepings));
    }
    }
