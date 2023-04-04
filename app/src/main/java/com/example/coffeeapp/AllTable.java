package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class AllTable extends AppCompatActivity {
    private RecyclerView rv_table;
    private ArrayList<String> tableList;
    private CustomTable customTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_table);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tableList = new ArrayList<>();
        tableList.add("Còn Chỗ");
        tableList.add("Hết Chỗ");
        tableList.add("Còn Chỗ");
        tableList.add("Còn Chỗ");
        tableList.add("Hết Chỗ");
        Log.d("aaaa", Integer.toString(tableList.size()));
        rv_table = findViewById(R.id.rv_ban);

        rv_table.setLayoutManager(new LinearLayoutManager(this));
        customTable = new CustomTable(tableList);
        rv_table.setAdapter(customTable);

    }
}