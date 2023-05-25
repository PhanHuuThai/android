package com.example.coffeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffeeapp.bean.Bill;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.bean.Table;
import com.example.coffeeapp.viewmodel.BillApiService;
import com.example.coffeeapp.viewmodel.StaffApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllBill extends AppCompatActivity {

    private RecyclerView rv_bill;
    private ArrayList<Bill> billList;
    private BillApiService billService;
    private CustomBill customBill;
    private Button bt_Menu, bt_Table,bt_Bill,bt_AboutMe;

    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    private String userName;
    private SharedPreferences sharedpreferences;
    private StaffApiService apiService;

    private EditText edName, edTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bill);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        billService = new BillApiService();
        apiService = new StaffApiService();
        billList = new ArrayList<>();

        edName = findViewById(R.id.edt_name);
        edTime = findViewById(R.id.edt_time);
        Date currentDate = new Date();

        // Định dạng ngày và giờ
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Chuyển đổi ngày và thời gian thành chuỗi
        String dateString = dateFormat.format(currentDate);
        edTime.setText(dateString);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userName = sharedpreferences.getString(EMAIL_KEY,null);

        Log.d("aaaa", Integer.toString(billList.size()));
        rv_bill = findViewById(R.id.rv_bill);

        apiService.GetAllStaff(userName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Staff>>() {
                    @Override
                    public void onSuccess(@NonNull List<Staff> staff) {
                        for (Staff item : staff){
                            edName.setText(item.getName());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });

        rv_bill.setLayoutManager(new LinearLayoutManager(this));
        customBill = new CustomBill(billList, AllBill.this);
        rv_bill.setAdapter(customBill);
        billService.getAllBill("All")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Bill>>() {
                    @Override
                    public void onSuccess(@NonNull List<Bill> bills) {
                        for(Bill item : bills){
                            billList.add(item);
                            customBill.notifyDataSetChanged();
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