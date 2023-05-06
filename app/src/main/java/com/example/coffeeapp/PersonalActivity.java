package com.example.coffeeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffeeapp.bean.Staff;
import com.example.coffeeapp.view.Login;
import com.example.coffeeapp.viewmodel.StaffApiService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PersonalActivity extends Activity {
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    private String userName;
    private SharedPreferences sharedpreferences;

    private Button bt_Menu, bt_Table,bt_Bill,bt_AboutMe, logout;
    private EditText edID, edTen, edNgaySinh, edSDT, edEmail, edDiachi;
    private StaffApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userName = sharedpreferences.getString(EMAIL_KEY,null);

        edID = findViewById(R.id.edit_ID);
        edTen = findViewById(R.id.edit_Name);
        edNgaySinh = findViewById(R.id.edit_DateBirth);
        edSDT = findViewById(R.id.edit_phone);
        edEmail = findViewById(R.id.edit_Mail);
        edDiachi = findViewById(R.id.edit_IDCard);

        apiService = new StaffApiService();

        apiService.GetAllStaff(userName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Staff>>() {
                    @Override
                    public void onSuccess(@NonNull List<Staff> staff) {
                        for (Staff item : staff){
                            edID.setText(item.getId());
                            edTen.setText(item.getName());
                            edNgaySinh.setText(item.getDayofbirth());
                            edSDT.setText(item.getPhonenumber());
                            edEmail.setText(item.getEmail());
                            edDiachi.setText(item.getAddress());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(PersonalActivity.this, "Không có thông tin !", Toast.LENGTH_SHORT).show();
                    }
                });

        bt_Menu= findViewById(R.id.button_Menu) ;
        bt_Menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PersonalActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        bt_Bill= findViewById(R.id.button_ListBill) ;
        bt_Bill.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PersonalActivity.this,AllBill.class);
                startActivity(intent);
            }
        });
        bt_AboutMe= findViewById(R.id.button_AboutMe) ;
        bt_Menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PersonalActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        bt_Table= findViewById(R.id.button_AllTable) ;
        bt_Table.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PersonalActivity.this,AllTable.class);
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(PersonalActivity.this, Login.class);
                startActivity(intent);
            }
        });


    }
}