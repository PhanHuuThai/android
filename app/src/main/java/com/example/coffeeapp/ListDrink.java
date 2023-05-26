package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.coffeeapp.bean.Bill;
import com.example.coffeeapp.bean.BillDetail;
import com.example.coffeeapp.model.productOrdered;
import com.example.coffeeapp.view.RevenueView;
import com.example.coffeeapp.viewmodel.BillApiService;
import com.example.coffeeapp.viewmodel.BillDetailApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListDrink extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    private String userName;
    private SharedPreferences sharedpreferences;

    private RecyclerView rv_listdrink;
    private ArrayList<BillDetail> listDrink;
    private ArrayList<BillDetail> orderDetail;
    private CustomBillDetail customBillDetail;
    private ImageView imgcoffee;
    private String nameTable, idTable;
    private ArrayList<Bill> billList;
    private Button huy, ThanhToan;

    private BillApiService billService;
    private BillDetailApiService billDetailApiService;

    private String lastIdOrder, lastIdOrderDetail;
    private String idOrder, idOrderDetail;
    private Double price = 0d;
    private TextInputEditText textchuthich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drink);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int check = 1;

        billService = new BillApiService();
        billDetailApiService = new BillDetailApiService();

        billList = new ArrayList<>();
        listDrink = new ArrayList<>();
        orderDetail = new ArrayList<>();

        huy = findViewById(R.id.btn_huy);

        Date currentDate = new Date();

        // Định dạng ngày và giờ
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Chuyển đổi ngày và thời gian thành chuỗi
        String dateString = dateFormat.format(currentDate);

        Log.d("datetime", dateString);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userName = sharedpreferences.getString(EMAIL_KEY,null);

        ThanhToan = findViewById(R.id.bt_thanhtoan);

        Intent e = getIntent();
        nameTable = e.getStringExtra("nameTable");
        idTable = e.getStringExtra("idTable");
        ArrayList<productOrdered> productss = (ArrayList<productOrdered>) e.getSerializableExtra("list");
        ArrayList<String> chuthich = (ArrayList<String>) e.getSerializableExtra("list2");
        for (productOrdered produc : productss){
            price += produc.getSalePrice()*produc.getQuantity();
        }
        Log.d("idTable", idTable);
        Log.d("tableName", nameTable);
        Log.d("username", userName);
        Log.d("productss12", productss.toString());
        Log.d("chuthich3", chuthich.toString());

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("check", check);
                intent.putExtra("productList", productss);
                intent.putExtra("chuthichlist", chuthich);
                setResult(RESULT_OK, intent);
                finish();
                //startActivity(intent);
            }
        });



        ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productss.size()==0){
                    Toast.makeText(ListDrink.this,"Chưa có sản phẩm trong giỏ hàng",Toast.LENGTH_SHORT).show();
                } else {
                    billService.getAllBill("All")
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<List<Bill>>() {
                                @Override
                                public void onSuccess(@NonNull List<Bill> bills) {
                                    for(Bill item : bills){
                                        billList.add(item);
                                        Log.d("idOrderLast2", billList.toString());
                                    }

                                    lastIdOrder = billList.get(billList.size()-1).getId();
                                    idOrder = getIdOrder(lastIdOrder);
                                    Log.d("idorder", idOrder);
                                    List<Object> list= new ArrayList<>();
                                    list.add(new Bill(idOrder, dateString, userName, price, idTable));
                                    billDetailApiService.getAllBillDetail(lastIdOrder)
                                            .subscribeOn(Schedulers.newThread())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribeWith(new DisposableSingleObserver<List<BillDetail>>() {
                                                @Override
                                                public void onSuccess(@NonNull List<BillDetail> billDetails) {
                                                    for(BillDetail item1 : billDetails){
                                                        orderDetail.add(item1);
                                                    }
                                                    Log.d("lastIdOrderDetail", orderDetail.toString());
                                                    lastIdOrderDetail = orderDetail.get(orderDetail.size()-1).getIdOrderDetail();

                                                    idOrderDetail = getIdOrderDetail(lastIdOrderDetail);
                                                    Log.d("idOrderDetail", idOrderDetail);
                                                    int count = 0;
                                                    for(productOrdered prod : productss){
                                                        list.add(new BillDetail(idOrderDetail, prod.getId(), idOrder, prod.getQuantity(), chuthich.get(count)));

                                                        idOrderDetail = getIdOrderDetail(idOrderDetail);
                                                        count++;
                                                    }
                                                    Log.d("listaddOrder", list.toString());
                                                    billService.addBill(list);
                                                    Intent e = new Intent(ListDrink.this, AllTable.class);
                                                    startActivity(e);
                                                }

                                                @Override
                                                public void onError(@NonNull Throwable e) {

                                                }
                                            });

                                }

                                @Override
                                public void onError(@NonNull Throwable e) {

                                }
                            });
                }




            }
        });
        int count = 0;

        textchuthich = findViewById(R.id.textChuthich);
        String ct = "";
        for (String i : chuthich){
            if(!i.equals("")){
                ct += productss.get(count).getName() + " " + i + ", ";
            }
            count++;
        }
        Log.d("chuthichthai2", ct);
        textchuthich.setText(ct);
        Log.d("chuthichthai", chuthich.toString());

        int n = 0;
        for(productOrdered pro : productss){
            listDrink.add(new BillDetail(idOrderDetail, pro.getId(), idOrder, pro.getQuantity(), chuthich.get(n)));
            n++;
        }




        imgcoffee = findViewById(R.id.imgcafe);
        imgcoffee.setImageResource(R.drawable.icon_coffee);



        //Log.d("aaaa", Integer.toString(listDrink.size()));
        rv_listdrink = findViewById(R.id.rv_listdrink);

        rv_listdrink.setLayoutManager(new LinearLayoutManager(this));
        customBillDetail = new CustomBillDetail(listDrink, productss);
        rv_listdrink.setAdapter(customBillDetail);

    }

    private static String getIdOrder(String id){
        String result= "";
        int a = Integer.parseInt(id.substring(2));
        a++;
        if(a < 10){
            result = id.substring(0,2) + "0" + Integer.toString(a);
        }
        else{
            result = id.substring(0,2) + Integer.toString(a);
        }
        return result;
    }

    private static String getIdOrderDetail(String id){
        String result= "";
        int a = Integer.parseInt(id.substring(4));
        a++;
        if(a < 10){
            result = id.substring(0,4) + "0" + Integer.toString(a);
        }
        else{
            result = id.substring(0,4) + Integer.toString(a);
        }
        return result;
    }

}
