package com.example.coffeeapp.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.coffeeapp.AllBill;
import com.example.coffeeapp.AllStaff;
import com.example.coffeeapp.CustomBill;
import com.example.coffeeapp.R;
import com.example.coffeeapp.bean.Bill;
import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.information_staff;
import com.example.coffeeapp.model.revenueM;
import com.example.coffeeapp.viewmodel.BillApiService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RevenueView extends AppCompatActivity {
    private TextView tvVenenueDay;
    private TextView tvVenenueMonth;
    private double sumVenenue = 0;
    private ArrayList<Bill> billList;
    //    private BillApiService billService;
    private EditText editTextDay;
    private TextView txttonghoadon;
    private BillApiService billApiService;
    private List<Entry> entries = new ArrayList<>();
    List<BarEntry> barEntryList  = new ArrayList<>();
    List<Bill> preResult = new ArrayList<>();
    private Map<String, Double> map;
    String regix = "^(\\d{1,2})[./-](\\d{4})$";
    private Button btnnhanvien, btndiemdanh, btnsanpham, btnthongtin, btndoanhthu, btnxemchitiet;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revenue_statistics);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnthongtin = findViewById(R.id.btn_thongtin);
        btnthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RevenueView.this, information_staff.class);
                startActivity(intent);
            }
        });

        btndiemdanh = findViewById(R.id.btn_diemdanh);
        btndiemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RevenueView.this, EmployeeTimekeepingV.class);
                startActivity(intent);
            }
        });
        btnsanpham = findViewById(R.id.btn_sanpham);
        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RevenueView.this, OrderByEmployee.class);
                startActivity(intent);
            }
        });
        btnnhanvien = findViewById(R.id.btn_nhanvien);
        btnnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RevenueView.this, AllStaff.class);
                startActivity(intent);
            }
        });
        editTextDay = findViewById(R.id.editTextDate);

        txttonghoadon = findViewById(R.id.txt_tongdoanhthu);
        btnxemchitiet = findViewById(R.id.btn_xemchitiet);
        BarChart BarChart = findViewById(R.id.bar_chart);
        btnxemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInvoiceListDialog(preResult);
            }
        });
        billApiService = new BillApiService();
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!String.valueOf(editTextDay.getText()).equals("")) {
                    Log.d("regix", "ok");
                    if (String.valueOf(editTextDay.getText()).matches(regix)) {
                        String date = String.valueOf(editTextDay.getText());
                        Log.d("DATEEEEE", date);
                        billApiService.getAllBill("All").subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread()).
                                subscribeWith(new DisposableSingleObserver<List<Bill>>() {
                                    @Override
                                    public void onSuccess(@NonNull List<Bill> bills) {
                                        sumVenenue= 0;
                                        Log.d("REVENUE", String.valueOf(bills.size()));
                                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                            preResult = bills.stream().filter(b -> b.getTime().contains(date)).collect(Collectors.toList());
                                            Log.d("REVENUE_1", String.valueOf(preResult.size()));
                                            for (Bill item:preResult){
                                                sumVenenue+=item.getPrice();
                                            }
                                            map = convertToMap(preResult);
                                            entries.removeAll(entries);
                                            barEntryList.removeAll(barEntryList);
                                            entries = map.entrySet().stream()
                                                    .map(e -> new Entry(Float.parseFloat(e.getKey().substring(0, 2)), e.getValue().floatValue()))
                                                    .collect(Collectors.toList());
                                        }
                                    }
                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.d("ERROR", "1");
                                    }
                                });

                    }



                }
                if(!entries.isEmpty()){
                    for(Entry item: entries){
                        barEntryList.add(new BarEntry(item.getX(),item.getY()));
                    }
                    Log.d("REVENUE_2",String.valueOf(barEntryList.size()));
                }
                else {
                    txttonghoadon.setText("");
                    BarChart.clear();
                }
                if(!barEntryList.isEmpty()){
                    BarDataSet dataSet = new BarDataSet(barEntryList, "Tổng tiền/Ngày");
                    BarData barData = new BarData(dataSet);
                    BarChart.setData(barData);
                    BarChart.getDescription().setEnabled(false);
                    BarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                    BarChart.getXAxis().setAxisMinimum(0f);
                    BarChart.getXAxis().setLabelCount(31);

                    BarChart.getAxisLeft().setAxisMinimum(0); // thiết lập giá trị nhỏ nhất của trục Y là 0
                    BarChart.getXAxis().setDrawGridLines(false); // ẩn lưới trên trục Y
                    BarChart.getAxisRight().setEnabled(false);
                    BarChart.getXAxis().setGranularity(1f);
                    BarChart.invalidate();
                    txttonghoadon.setText(String.valueOf(sumVenenue)+" VND");
                }
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);





















    }
    private Map<String, Double> convertToMap(List<Bill> bills) {
        Map<String, Double> result = new HashMap<>();
        for (Bill item : bills) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                result.computeIfPresent(item.getTime(), (k, v) -> v + item.getPrice());
                result.putIfAbsent(item.getTime(), item.getPrice());
            }
        }
        return result;

    }
    private  void showInvoiceListDialog(List<Bill> invoiceList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Danh sách hoá đơn");

        // Tạo danh sách ListView và đặt adapter cho nó
        ListView listView = new ListView(this);
        ArrayAdapter<Bill> adapter = new ArrayAdapter<Bill>(this, android.R.layout.simple_list_item_1, invoiceList);
        listView.setAdapter(adapter);

        // Đặt danh sách ListView vào dialog
        builder.setView(listView);

        // Thêm button "Đóng" và đóng dialog khi nhấn vào
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Hiển thị dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
