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
import android.widget.EditText;
import android.widget.Toast;


import com.example.coffeeapp.bean.Bill;
import com.example.coffeeapp.bean.BillDetail;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.viewmodel.BillApiService;
import com.example.coffeeapp.viewmodel.BillDetailApiService;
import com.example.coffeeapp.viewmodel.ProductOrderApiService;
import com.example.coffeeapp.viewmodel.StaffApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BillDetailView extends AppCompatActivity {

    private RecyclerView rv_billdetail;
    private ArrayList<Bill> billList;
    private ArrayList<BillDetail> billDetailsList;
    private ArrayList<productOrdered> productOrderedsList;
    private CustomBillDetail customBillDetail;
    private Button btn_Thoat;
    private BillDetailApiService billDetailApiService;
    private BillApiService billService;
    private StaffApiService apiService;
    private ProductOrderApiService productOrderApiService;
    private String idOrder;
    private EditText edtNgay, edtNV, edidhd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);

        billDetailApiService = new BillDetailApiService();
        billService = new BillApiService();
        apiService = new StaffApiService();
        productOrderApiService = new ProductOrderApiService();

        edtNgay = findViewById(R.id.edt_ngaythang);
        edtNV = findViewById(R.id.edt_tenNV);
        edidhd = findViewById(R.id.edt_billID);

        billList = new ArrayList<>();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent e = getIntent();
        if(e != null){
            idOrder = e.getStringExtra("idOrder");
        }
        Log.d("thai", idOrder);

        edidhd.setText(idOrder);

        billService.getAllBill("All")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Bill>>() {
                    @Override
                    public void onSuccess(@NonNull List<Bill> bills) {
                        for(Bill item : bills){
                            billList.add(item);
                        }
                        edtNgay.setText(billList.get(0).getTime());
                        apiService.GetAllStaff(billList.get(0).getIdEmployee()).subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableSingleObserver<List<Staff>>() {
                                    @Override
                                    public void onSuccess(@NonNull List<Staff> staff) {
                                        for (Staff item : staff){
                                            edtNV.setText(item.getName());
                                        }
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Toast.makeText(BillDetailView.this, "Không có thông tin !", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

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