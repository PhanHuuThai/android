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

import com.example.coffeeapp.bean.Bill;

import java.util.ArrayList;

public class AllBill extends AppCompatActivity {

    private RecyclerView rv_bill;
    private ArrayList<Bill> billList;
    private CustomBill customBill;
    private Button bt_Menu, bt_Table,bt_Bill,bt_AboutMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bill);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        billList = new ArrayList<>();
        billList.add(new Bill("HD01", 70000, "15.44"));
        billList.add(new Bill("HD02", 65000, "16.00"));
        billList.add(new Bill("HD03", 50000, "16.25"));
        billList.add(new Bill("HD04", 45000, "15.30"));

        Log.d("aaaa", Integer.toString(billList.size()));
        rv_bill = findViewById(R.id.rv_bill);

        rv_bill.setLayoutManager(new LinearLayoutManager(this));
        customBill = new CustomBill(billList, AllBill.this);
        rv_bill.setAdapter(customBill);

        bt_Menu= findViewById(R.id.button_Menu) ;
        bt_Menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AllBill.this, MenuActivity.class);
                startActivity(intent);

            }
        });
        bt_Bill= findViewById(R.id.button_ListBill) ;
        bt_Bill.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AllBill.this,AllBill.class);
                startActivity(intent);
            }
        });
        bt_AboutMe= findViewById(R.id.button_AboutMe) ;
        bt_AboutMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AllBill.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
        bt_Table= findViewById(R.id.button_AllTable) ;
        bt_Table.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AllBill.this,AllTable.class);
                startActivity(intent);
            }
        });
    }
}