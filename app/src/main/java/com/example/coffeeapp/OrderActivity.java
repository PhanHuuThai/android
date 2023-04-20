package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.coffeeapp.bean.Product;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity
{
    private RecyclerView rv_product ;
    private ArrayList<Product> productsList ;
    private ProductAdapter productAdapter ;
    private AlertDialog.Builder dialogbuildder ;
    private AlertDialog dialog ;
    private TextInputEditText txtsize , txtsoluong,txtchuthich ;
    private Button bt_huy, bt_them,btorder ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        productsList= new ArrayList<>() ;
        final  View AddproductPopup = getLayoutInflater().inflate(R.layout.addproduct_popup,null) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productsList.add(new Product("Huda",30000,"R.drawable.hampogar",100)) ;
        productAdapter= new ProductAdapter(productsList,this,AddproductPopup) ;
        rv_product= findViewById(R.id.rv_product) ;
        rv_product.setLayoutManager(( new GridLayoutManager(this,2)));
        rv_product.setAdapter(productAdapter);


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