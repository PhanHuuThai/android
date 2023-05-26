package com.example.coffeeapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.coffeeapp.viewmodel.StaffApiService;
import com.squareup.picasso.Picasso;

public class personal_information extends Activity {
    private EditText id,name,date,phone,email,adress,salary;
    private ImageView image;
    private Button btn_back,btnluu;
    private StaffApiService apiService;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        Intent in = getIntent();
        id= findViewById(R.id.edit_ID);
        name = findViewById(R.id.edit_Name);
        date = findViewById(R.id.edit_DateBirth);
        phone = findViewById(R.id.edit_phone);
        email = findViewById(R.id.edit_Mail);
        adress = findViewById(R.id.edit_IDdiachi);
        image = findViewById(R.id.img_personal);
        salary = findViewById(R.id.edit_luong);
        btn_back = findViewById(R.id.btnback2);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(personal_information.this, AllStaff.class);
                startActivity(intent);
            }
        });
        btnluu = findViewById(R.id.btn_Luu);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService = new StaffApiService();
                Log.d("test44"," "+in.getIntExtra("Numberworkingday",0));
                apiService.editEmployee(String.valueOf(id.getText()),String.valueOf(name.getText()),String.valueOf(phone.getText()),String.valueOf(email.getText()),String.valueOf(date.getText()),String.valueOf(adress.getText()),in.getStringExtra("Image"),Integer.parseInt(String.valueOf(salary.getText())),in.getIntExtra("Numberworkingday",0));
                Intent intent= new Intent(personal_information.this, AllStaff.class);
                startActivity(intent);

            }
        });


        Log.d("DEBUG","personal_information");
        id.setText(in.getStringExtra("id"));
        name.setText(in.getStringExtra("Fullname"));
        date.setText(in.getStringExtra("Dayofbirth"));
        phone.setText(in.getStringExtra("Phonenumber"));
        email.setText(in.getStringExtra("email"));
        adress.setText(in.getStringExtra("Address"));
        if(!in.getStringExtra("Image").equals("")) {
            Picasso.get().load(in.getStringExtra("Image")).resize(200, 200).into(image);
        }
        salary.setText(String.valueOf(in.getIntExtra("Salary",0)*in.getIntExtra("Numberworkingday",0)));
        Log.d("DEBUG","personal_information_2");

    }
    private static String getIdStaff(String id){
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
}