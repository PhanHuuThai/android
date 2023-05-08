package com.example.coffeeapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.coffeeapp.AllBill;
import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.CustomBill;
import com.example.coffeeapp.R;
import com.example.coffeeapp.bean.Bill;
import com.example.coffeeapp.information_staff;
import com.example.coffeeapp.model.revenueM;
import com.example.coffeeapp.viewmodel.BillApiService;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RevenueView extends AppCompatActivity {
    private TextView tvVenenueDay;
    private TextView tvVenenueMonth;
    private  double sumVenenue =0;
    private ArrayList<Bill> billList;
    private BillApiService billService;
    private EditText editTextDay;
    private  TextView txttonghoadon;
    private  BillApiService billApiService;
    private List<Bill> bills;
    private Map<String,Integer> map;
    String regix = "^(\\d{1,2})[./-](\\d{4})$";
    private Button btnnhanvien,btndiemdanh,btnsanpham,btnthongtin,btndoanhthu,btnxemchitiet;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revenue_statistics);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnthongtin= findViewById(R.id.btn_thongtin);
        btnthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, information_staff.class);
                startActivity(intent);
            }
        });

        btndiemdanh = findViewById(R.id.btn_diemdanh);
        btndiemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, EmployeeTimekeepingV.class);
                startActivity(intent);
            }
        });
        btnsanpham = findViewById(R.id.btn_sanpham);
        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, OrderByEmployee.class);
                startActivity(intent);
            }
        });
        btnnhanvien = findViewById(R.id.btn_nhanvien);
        btnnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, AllStaff.class);
                startActivity(intent);
            }
        });

        billService = new BillApiService();

        billList = new ArrayList<>();

        Log.d("aaaa", Integer.toString(billList.size()));

        billService.getAllBill("All")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Bill>>() {
                    @Override
                    public void onSuccess(@NonNull List<Bill> bills) {
                        for(Bill item : bills){
                            billList.add(item);

                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        editTextDay = findViewById(R.id.editTextDate);
        txttonghoadon = findViewById(R.id.txt_tongdoanhthu);
        btnxemchitiet = findViewById(R.id.btn_xemchitiet);
        btnxemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RevenueView.this, AllBill.class);
                startActivity(intent);
            }
        });

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                billApiService = new BillApiService();
                bills  = new ArrayList<>();
                map = new HashMap<>();
                billApiService.getAllBill("All").subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread()).
                        subscribeWith(new DisposableSingleObserver<List<Bill>>() {
                            @Override
                            public void onSuccess(@NonNull List<Bill> billss) {
                                for(Bill item :billss){
                                    bills.add(item);
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });

                if(!String.valueOf(editTextDay.getText()).equals("") ){
                    if(String.valueOf(editTextDay.getText()).matches(regix)){
                        String date = String.valueOf(editTextDay.getText());


                    }
                    else{
                        Toast.makeText(RevenueView.this,"Định dạng ngày không hợp lệ",Toast.LENGTH_SHORT).show();
                    }
                }
                handler.postDelayed(this, 10000);
            }
        };
        handler.post(runnable);



        LineChart lineChart = findViewById(R.id.line_chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 10));
        entries.add(new Entry(1, 20));
        entries.add(new Entry(2, 30));
        entries.add(new Entry(3, 40));

        LineDataSet dataSet = new LineDataSet(entries, "Label");

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false); // remove description label
        lineChart.getLegend().setEnabled(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // move X-axis to the bottom
        lineChart.getXAxis().setDrawGridLines(false); // disable vertical grid lines
        lineChart.getAxisRight().setEnabled(false); // disable right Y-axis


        lineChart.invalidate(); // refresh the chart









    }
}
