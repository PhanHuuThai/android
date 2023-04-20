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

import java.util.ArrayList;

public class AllTable extends AppCompatActivity {
    private RecyclerView rv_table;
    private ArrayList<String> tableList;
    private CustomTable customTable;
    private Button bt_Menu, bt_Table,bt_Bill,bt_AboutMe;

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

        bt_Menu= findViewById(R.id.button_Menu) ;
        bt_Menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AllTable.this, MenuActivity.class);
                startActivity(intent);

            }
        });
        bt_Bill= findViewById(R.id.button_ListBill) ;
        bt_Bill.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AllTable.this,AllBill.class);
                startActivity(intent);
            }
        });
        bt_AboutMe= findViewById(R.id.button_AboutMe) ;
        bt_AboutMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AllTable.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
        bt_Table= findViewById(R.id.button_AllTable) ;
        bt_Table.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AllTable.this,AllTable.class);
                startActivity(intent);
            }
        });

    }
}