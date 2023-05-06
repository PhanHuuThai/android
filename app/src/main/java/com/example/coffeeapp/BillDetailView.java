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


import com.example.coffeeapp.bean.BillDetail;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.viewmodel.BillDetailApiService;
import com.example.coffeeapp.viewmodel.ProductOrderApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BillDetailView extends AppCompatActivity {

    private RecyclerView rv_billdetail;
    private ArrayList<BillDetail> billDetailsList;
    private ArrayList<productOrdered> productOrderedsList;
    private CustomBillDetail customBillDetail;
    private Button btn_Thoat;
    private BillDetailApiService billDetailApiService;
    private ProductOrderApiService productOrderApiService;
    private String idOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);

        billDetailApiService = new BillDetailApiService();
        productOrderApiService = new ProductOrderApiService();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent e = getIntent();
        if(e != null){
            idOrder = e.getStringExtra("idOrder");
        }
        Log.d("thai", idOrder);


        billDetailsList = new ArrayList<>();
        productOrderedsList = new ArrayList<>();



        rv_billdetail = findViewById(R.id.rv_billdetail);
        btn_Thoat = findViewById(R.id.btn_thoat);



        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(BillDetailView.this, AllBill.class);
                startActivity(e);
            }
        });

        rv_billdetail.setLayoutManager(new LinearLayoutManager(this));
        customBillDetail = new CustomBillDetail(billDetailsList, productOrderedsList);
        rv_billdetail.setAdapter(customBillDetail);
        billDetailApiService.getAllBillDetail(idOrder)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<BillDetail>>() {
                    @Override
                    public void onSuccess(@NonNull List<BillDetail> billDetails) {
                        for (BillDetail billDetail : billDetails){
                            billDetailsList.add(billDetail);
                            //customBillDetail.notifyDataSetChanged();
                            productOrderApiService.GetAllPBO(billDetail.getIdProduct())
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeWith(new DisposableSingleObserver<List<productOrdered>>() {
                                        @Override
                                        public void onSuccess(@NonNull List<productOrdered> productOrdereds) {
                                            for(productOrdered product : productOrdereds){
                                                productOrderedsList.add(product);
                                                customBillDetail.notifyDataSetChanged();
                                                Log.d("qq", "sucsess");
                                            }
                                        }

                                        @Override
                                        public void onError(@NonNull Throwable e) {
                                            e.printStackTrace();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


    }
}