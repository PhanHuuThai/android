package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.coffeeapp.bean.Product;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.viewmodel.ProductOrderApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity
{
    private RecyclerView rv_product ;
    private ArrayList<productOrdered> productsList ;
    private ProductAdapter productAdapter ;
    private AlertDialog.Builder dialogbuildder ;
    private ProductOrderApiService productService;
    private AlertDialog dialog ;
    private TextInputEditText txtsize , txtsoluong,txtchuthich ;
    private Button bt_huy, bt_them,btorder ;
    private TextView tvTenBan;
    private String nameTable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent e = getIntent();
        nameTable = e.getStringExtra("nameTable");
        Log.d("sss", nameTable);


        productService = new ProductOrderApiService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tvTenBan = findViewById(R.id.txtTenBan);
        tvTenBan.setText(nameTable);
        productsList= new ArrayList<>() ;
        final  View AddproductPopup = getLayoutInflater().inflate(R.layout.addproduct_popup,null) ;

        productAdapter= new ProductAdapter(productsList,this,AddproductPopup) ;
        rv_product= findViewById(R.id.rv_product) ;
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




    }
//    public void Addproduct()
//    {
//        dialogbuildder= new AlertDialog.Builder(this) ;
//        final  View AddproductPopup = getLayoutInflater().inflate(R.layout.addproduct_popup,null) ;
////        txtsize = (TextInputEditText) AddproductPopup.findViewById(R.id.popup_txtsize) ;
////        txtsoluong = (TextInputEditText) AddproductPopup.findViewById(R.id.popup_txtsoluong) ;
////        txtchuthich = (TextInputEditText) AddproductPopup.findViewById(R.id.popup_txtchuthich) ;
//        dialogbuildder.setView(AddproductPopup) ;
//        dialog=dialogbuildder.create();
//        dialog.show();
//    }
}