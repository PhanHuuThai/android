package com.example.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coffeeapp.bean.Product;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView rv_product ;
    private ArrayList<Product> productsList ;
    private MenuAdapter productAdapter ;
    private Button bt_Menu, bt_Table,bt_Bill,bt_AboutMe;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        productsList= new ArrayList<>() ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productAdapter= new MenuAdapter(productsList) ;
        rv_product= findViewById(R.id.rv_product1) ;
        rv_product.setLayoutManager(( new GridLayoutManager(this,2)));
        rv_product.setAdapter(productAdapter);
        bt_Menu= findViewById(R.id.button_Menu) ;
        bt_Menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
        bt_Bill= findViewById(R.id.button_ListBill) ;
        bt_Bill.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivity.this,AllBill.class);
                startActivity(intent);
            }
        });
        bt_AboutMe= findViewById(R.id.button_AboutMe) ;
        bt_AboutMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
        bt_Table= findViewById(R.id.button_AllTable) ;
        bt_Table.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivity.this,AllTable.class);
                startActivity(intent);
            }
        });
    }
}