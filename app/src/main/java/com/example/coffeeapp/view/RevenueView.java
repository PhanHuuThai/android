package com.example.coffeeapp.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeapp.R;
import com.example.coffeeapp.model.revenueM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RevenueView extends AppCompatActivity {
    private TextView tvVenenueDay;
    private TextView tvVenenueMonth;
    private  double sumVenenue =0;
    private EditText editTextDay;
    private EditText editTextMonth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revenue_statistics);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        List<revenueM> revenueMList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            revenueMList.add(new revenueM(LocalDate.of(2023, 12, 5), 200000));
            revenueMList.add(new revenueM(LocalDate.of(2023, 12, 5), 200000));
            revenueMList.add(new revenueM(LocalDate.of(2023, 12, 5), 200000));
            revenueMList.add(new revenueM(LocalDate.of(2023, 12, 5), 200000));
        }
        editTextDay = (EditText) findViewById(R.id.editTextDateDay);
        for (revenueM item : revenueMList
        ) {
            System.out.println(editTextDay.getText());
            System.out.println(String.valueOf(item.getDate()));
            if(editTextDay.getText().equals(String.valueOf(item.getDate()))){
                sumVenenue += item.getRevenue();
            }

        }
        tvVenenueDay = (TextView) findViewById(R.id.tvRenevueDay);
        tvVenenueDay.setText(String.valueOf(sumVenenue));
        sumVenenue = 0;
//        for (revenueM item : revenueMList
//        ) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                if(editTextMonth.getText().toString().contains(item.getDate().getMonth().toString())){
//                    sumVenenue += item.getRevenue();
//                }
//            }
//
//        }
        tvVenenueMonth = (TextView) findViewById(R.id.tvRenevueMonth);
        tvVenenueMonth.setText(String.valueOf(sumVenenue));
    }
}
