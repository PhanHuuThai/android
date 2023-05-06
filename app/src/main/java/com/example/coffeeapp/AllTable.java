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

import com.example.coffeeapp.bean.Table;
import com.example.coffeeapp.viewmodel.TableApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllTable extends AppCompatActivity {
    private RecyclerView rv_table;
    private ArrayList<Table> tableList;
    private CustomTable customTable;
    private TableApiService tableService;
    private Button bt_Menu, bt_Table,bt_Bill,bt_AboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_table);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tableService = new TableApiService();

        tableList = new ArrayList<>();
        Log.d("aaaa", Integer.toString(tableList.size()));
        rv_table = findViewById(R.id.rv_ban);

        rv_table.setLayoutManager(new LinearLayoutManager(this));
        customTable = new CustomTable(tableList, AllTable.this);
        rv_table.setAdapter(customTable);
        tableService.getAllTable("All")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Table>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(@NonNull List<Table> tables) {
                        for(Table item : tables){
                            Log.d("DEBUB","TABLESUCCESS!");
                            tableList.add(item);
                            customTable.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUB","TABLESUCCESS!");
                        e.printStackTrace();
                    }
                });

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