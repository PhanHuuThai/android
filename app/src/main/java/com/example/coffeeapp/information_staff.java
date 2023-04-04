package com.example.coffeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class information_staff extends Activity {
    private Button btnallstaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information_staff);
        btnallstaff = findViewById(R.id.btn_all_staff);
        btnallstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(information_staff.this,AllStaff.class);
                startActivity(intent);
            }
        });
    }

}