package com.example.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coffeeapp.bean.Product;
import com.example.coffeeapp.bean.Table;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.viewmodel.ProductOrderApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView rv_product ;
    private ArrayList<productOrdered> productsList ;
    private ProductOrderApiService productService;
    private MenuAdapter productAdapter ;
    private Button bt_Menu, bt_Table,bt_Bill,bt_AboutMe;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        productService = new ProductOrderApiService();

        productsList= new ArrayList<>() ;

        productAdapter= new MenuAdapter(productsList) ;
        rv_product= findViewById(R.id.rv_product1) ;
        rv_product.setLayoutManager(( new GridLayoutManager(this,2)));
        rv_product.setAdapter(productAdapter);

        productService.GetAllPBO("All")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<productOrdered>>() {
                    @Override
                    public void onSuccess(@NonNull List<productOrdered> productOrdereds) {
                        for(productOrdered item : productOrdereds){
                            Log.d("DEBUB","TABLESUCCESS!");
                            productsList.add(item);
                            productAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

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